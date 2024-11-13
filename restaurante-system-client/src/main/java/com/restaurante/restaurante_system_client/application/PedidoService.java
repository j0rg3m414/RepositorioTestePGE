package com.restaurante.restaurante_system_client.application;

import com.restaurante.restaurante_system_client.application.converter.PedidoConverter;
import com.restaurante.restaurante_system_client.application.ex.EnvioPedidoFilaException;
import com.restaurante.restaurante_system_client.application.ex.GetPedidoException;
import com.restaurante.restaurante_system_client.application.ex.ProdutoNaoEncontradoException;
import com.restaurante.restaurante_system_client.application.representation.PedidoGetRequest;
import com.restaurante.restaurante_system_client.application.representation.PedidoSaveRequest;
import com.restaurante.restaurante_system_client.domain.*;
import com.restaurante.restaurante_system_client.domain.enumns.StatusPedido;
import com.restaurante.restaurante_system_client.infra.mqueue.PublisherFilaPedidos;
import com.restaurante.restaurante_system_client.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe responsável pela lógica do negócio sobre Pedidos
 * O Lombok irá criar o construtor em tempo de compilação
 * @author Jorge Mdaia
 * @since 31/10/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService{

    /* Dependência Obrigatória*/
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produdoRepository;
    private final GarcomRepository garcomRepository;
    private final MesaRepository mesaRepository;
    private final PublisherFilaPedidos publisherFilaPedidos;
    private final PedidoConverter pedidoConverter;
    private final ItemPedidoRepository itemPedidoRepository;

    /**
     * Irá atender o endpoint de registro do pedido de uma mesa
     * @return Pedido
     */
    @Transactional
    public Pedido save(PedidoSaveRequest request){
        Pedido pedido = montaPedido(request);
        try{
            return pedidoRepository.save(pedido);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Montagem do pedido de acordo com o que recebemos no json
     * @return Pedido
     */
    private Pedido montaPedido(PedidoSaveRequest request) {
        List<Produto> produtos = this.produdoRepository.findAllById(request.getIdProdutos());
        if(!produtos.isEmpty()){
            Optional<Garcom> garcom = garcomRepository.findById(request.getGarcom());
            Optional<Mesa> mesa = mesaRepository.findById(request.getMesa());
            Pedido pedido = new Pedido(garcom.get(), mesa.get());
            List<ItemPedido> itensPedido = produtos.stream().map(p ->
                            new ItemPedido(p,pedido,p.getPreco(),true))
                    .collect(Collectors.toList());
            pedido.setItensPedido(itensPedido);
            pedido.setStatus(StatusPedido.RECEBIDO);
            return pedido;
        }
        throw new ProdutoNaoEncontradoException("Produto(s) não encontrado(s).");
    }

    /**
     * Método que lista os todos os pedidos
     *
     * @return pedidos
     */
    @Transactional
    public List<Pedido> getAllPedidos(){
        return pedidoRepository.findAll();
    }

    /**
     * Método que detalha o pedido de uma mesa
     *
     * @return pedidos
     */
    @Transactional
    public Pedido getDadosPedido(Long idPedido){
        return pedidoRepository.getById(idPedido);
    }

    /**
     * Usado pelo ms-cozinha
     * para informar a produção de um produto (item do pedido)
     * @param numPedido
     * @return
     */
    @Transactional
    public ResponseEntity<PedidoGetRequest> getPedidoByNumPedido(Integer numPedido) throws GetPedidoException {
        try {
            Pedido pedido = pedidoRepository.findBynumpedido(numPedido);
            if(pedido != null) return PedidoConverter.convertToDTO(pedido);
        } catch (Exception e){
            throw new GetPedidoException();
        }
        throw new GetPedidoException();
    }

    /**
     * Método responsável para enviar o pedido recém-criado para fila no RabbitMQ
     * @param pedido
     */
    @Transactional()
    public void enviarPedidoParaFila(Pedido pedido){
        try{
            //Por algum motivo o numero do pedido não estava sendo preenchido no save
            //Mas levando em consideração que sempre são gerados juntos, o numero do pedido sempre vai ser igual ao id
            pedido.setNumpedido(pedido.getId().intValue());
            publisherFilaPedidos.enviarPedidoFila(pedidoConverter.convertToDTO(pedido).getBody());
        }catch(Exception e){
            //Escolhido Exception, pois pode ser que o rabbitmq esteja fora também.
            throw new EnvioPedidoFilaException(e.getMessage());
        }
    }

    /**
     * Envia uma lista de pedidos
     * @param listaPedidos<Pedido>
     */
    public void enviarListaPedidoParaFila(List<Pedido> listaPedidos){
        try{
            publisherFilaPedidos.enviarListaPedidosFila(pedidoConverter.convertToDTOListPedidos(listaPedidos));
        }catch(Exception e){
            //Escolhido Exception, pois pode ser que o rabbitmq esteja fora também.
            throw new EnvioPedidoFilaException(e.getMessage());
        }
    }

    /**
     * Método usado para alterar o status do pedido durante o ciclo de vida do mesmo
     * @param numPedido
     * @param novoStatus
     */
    @Transactional
    public Pedido updateStatusPedido(Integer numPedido, String novoStatus) {
        Pedido pedido = pedidoRepository.findBynumpedido(numPedido);
        //definindo status do pedido
        if(novoStatus != null) {
            switch (novoStatus) {
                case "RECEBIDO":
                    pedido.setStatus(StatusPedido.RECEBIDO);
                break;
                case "PREPARANDO":
                    pedido.setStatus(StatusPedido.PREPARANDO);
                    break;
                case "PRONTO":
                    pedido.setStatus(StatusPedido.PRONTO);
                    break;
                case "ENTREGUE":
                    pedido.setStatus(StatusPedido.ENTREGUE);
                    break;
                default:
                    pedido.setStatus(StatusPedido.INDEFINIDO);
            }
        }
        pedido.getItensPedido().forEach(p -> p.setStatus(true));
        pedidoRepository.save(pedido);
        return pedido;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deletePedido(Long idPedido) {
        pedidoRepository.deleteById(idPedido);
        log.info("--> Pedido deletado.");
    }

}

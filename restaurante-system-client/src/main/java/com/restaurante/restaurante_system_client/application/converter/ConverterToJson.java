package com.restaurante.restaurante_system_client.application.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.restaurante_system_client.application.representation.PedidoGetRequest;
import org.springframework.stereotype.Component;

/**
 * Classe para converter o dto de pedido em json
 * @author Jorge Maia
 * @since 07/11/2024
 */
@Component
public class ConverterToJson {

    /**
     * Método publico de conversão de dto para json
     * @author Jorge Maia
     * @since 07/11/2024
     */
    public String convertToJson(PedidoGetRequest dadosPedidos) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dadosPedidos);
        return json;
    }
}

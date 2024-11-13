package com.restaurante.mscozinha.application.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.mscozinha.domain.model.PedidoGetRequest;
import org.springframework.stereotype.Component;

@Component
public class ConverterToJson {

    public String convertToJson(Object dadosPedidos) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dadosPedidos);
        return json;
    }
}

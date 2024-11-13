package com.restaurante.mscozinha.application.ex;

import lombok.Getter;

public class ErroComunicacaoMicroservicesException extends Exception{

    @Getter
    private int status;

    public ErroComunicacaoMicroservicesException(String msg, int statusE) {
        super(msg);
        this.status = status;
    }
}

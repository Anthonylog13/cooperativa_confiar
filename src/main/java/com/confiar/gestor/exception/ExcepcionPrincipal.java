package com.confiar.gestor.exception;


import lombok.Getter;

@Getter
public class ExcepcionPrincipal extends RuntimeException {
    private final String codigo;

    public ExcepcionPrincipal(String mensaje, String codigo) {
        super(mensaje);
        this.codigo = codigo;
    }
}



package com.confiar.gestor.exception;

public class RecursoNoEncontradoException extends ExcepcionPrincipal {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje, "RECURSO_NO_ENCONTRADO");
    }
}
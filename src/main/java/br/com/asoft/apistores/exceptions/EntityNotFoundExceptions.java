package br.com.asoft.apistores.exceptions;

import java.io.Serializable;

public class EntityNotFoundExceptions extends BusinessException implements Serializable {

    public EntityNotFoundExceptions(String message) {
        super(message);
    }
    public EntityNotFoundExceptions(String entity,Long id) {
        this(String.format("Não existe um cadastro de %s com o código %d",entity,id));
    }

}

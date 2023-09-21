package br.com.asoft.apistores.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Fields {

    private String name;

    private String userMessage;
}

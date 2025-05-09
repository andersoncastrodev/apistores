package br.com.asoft.apistores.exceptionhandler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("Recurso não encontrado"),
    DADOS_INVALIDOS("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."),
    PARAMETRO_INVALIDO("Parâmetros inválido. Algum dos campos estão com valores incorretos"),
    PARAMETRO_OBRIGATORIOS("Um ou mais campos obrigatorios estão faltando. Corrija e tente novamente."),
    ERRO_NEGOCIO("Violação de regra de negócio"),
    URL_NAO_ENCONTRADA("Url não encontrada ou inválida"),
    ERRO_DE_SISTEMA("Erro de sistema"),
    TIPO_INVALIDO("Um ou mais campos estão com valores inválidos para o tipo do campo requerido. Corrija o valor e tente novamente."),
    TIPO_METODO_INVALIDO ("Método não permitido."),
    ACESSO_NEGADO("Acesso negado. Usuario sem permissão."),
    HELP("https://asoft.com.br/help");

    private final String type;
}

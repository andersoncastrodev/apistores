package br.com.asoft.apistores.filter;

import br.com.asoft.apistores.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoFilter {

    private Long id;

    private String descricao;

    private TipoPagamento tipoPagamento;

}

package br.com.asoft.apistores.inp;

import br.com.asoft.apistores.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInp {

    private Long id;
    private String descricao;
    private TipoPagamento tipoPagamento;

}

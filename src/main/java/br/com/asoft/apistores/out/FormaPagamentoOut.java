package br.com.asoft.apistores.out;

import br.com.asoft.apistores.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoOut {

    private Long id;
    private String descricao;
    private TipoPagamento pagamento;

}

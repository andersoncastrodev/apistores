package br.com.asoft.apistores.out;

import br.com.asoft.apistores.model.Venda;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ItenVendaOut {

    private Long id;

    private Venda venda;

    private Long quant;

    private BigDecimal valorUnid;

    private BigDecimal valorTotal;

}

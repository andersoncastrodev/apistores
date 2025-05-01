package br.com.asoft.apistores.out;

import br.com.asoft.apistores.model.Sales;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemVendaOut {

    private Long id;

    private Sales sales;

    private Long quant;

    private BigDecimal valorUnid;

    private BigDecimal valorTotal;

}

package br.com.asoft.apistores.inp;

import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.model.Venda;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemVendaInp {

    @NotNull
    private VendaInpId venda;

    @NotNull
    private ProdutoInpId produto;

    @NotNull
    private Long quant;

    @NotNull
    private BigDecimal valorUnid;

    @NotNull
    private BigDecimal valorTotal;

}

package br.com.asoft.apistores.inp;

import br.com.asoft.apistores.enums.TipoPagamento;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendaInp {


/*    @NotBlank
    private Long id;*/

    @NotNull
    private UsuarioInpId usuario;

    @NotNull
    private ClienteInpId cliente;

    @NotNull
    private BigDecimal valorTotal;

    private TipoPagamento tipoPagamento;

}

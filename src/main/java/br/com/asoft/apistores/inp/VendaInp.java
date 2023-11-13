package br.com.asoft.apistores.inp;

import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.model.Usuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendaInp {


/*    @NotBlank
    private Long id;*/

    @NotNull
    private Usuario usuario;

    @NotNull
    private Cliente cliente;

    @NotBlank
    private BigDecimal valorTotal;

}

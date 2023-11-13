package br.com.asoft.apistores.inp;

import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.model.Usuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendaInp {


    private Long id;

    private Usuario usuario;

    private Cliente cliente;

    private BigDecimal valorTotal;

}

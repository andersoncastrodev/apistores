package br.com.asoft.apistores.out;


import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendaOut {

    private Long id;

    private Usuario usuario;

    private Cliente cliente;

    private BigDecimal valorTotal;

}

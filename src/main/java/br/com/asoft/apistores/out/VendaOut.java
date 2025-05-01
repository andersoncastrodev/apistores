package br.com.asoft.apistores.out;


import br.com.asoft.apistores.enums.TipoPagamento;
import br.com.asoft.apistores.model.Client;
import br.com.asoft.apistores.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendaOut {

    private Long id;

    private Usuario usuario;

    private Client client;

    private BigDecimal valorTotal;

    private TipoPagamento tipoPagamento;

}

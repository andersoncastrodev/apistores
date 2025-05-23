package br.com.asoft.apistores.out;


import br.com.asoft.apistores.enums.TypePayment;
import br.com.asoft.apistores.model.Client;
import br.com.asoft.apistores.model.Users;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendaOut {

    private Long id;

    private Users users;

    private Client client;

    private BigDecimal valorTotal;

    private TypePayment typePayment;

}

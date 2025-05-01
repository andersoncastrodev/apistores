package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_cod")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cliente_cod")
    private Client client;

    private BigDecimal valorTotal;

    private TipoPagamento tipoPagamento;

}

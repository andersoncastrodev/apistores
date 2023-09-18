package br.com.asoft.apistores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private String descricao;

    private BigDecimal valorCompra;

    private BigDecimal valorVenda;

}
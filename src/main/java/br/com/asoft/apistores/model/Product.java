package br.com.asoft.apistores.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    private Long quant;

    private Long quantMin;

    private Long quantMax;

    private Long quantStock; // quantidade em estoque

    private Long quantMinStock;

    private String unit; // kg, g, l etc

    private Double weight;

    private BigDecimal valueBuy; // valor de compra

    private BigDecimal valueSell; // valor de venda

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier; //fornecedor

}

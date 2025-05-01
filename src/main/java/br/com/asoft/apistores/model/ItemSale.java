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
public class ItemSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Long quant;

    private BigDecimal valueUnid;

    private BigDecimal valueTotal;

    @ManyToOne
    @JoinColumn(name = "id_sales")
    private Sales sales;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;


}

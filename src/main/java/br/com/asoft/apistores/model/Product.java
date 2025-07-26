package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.StatusValue;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

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

    private String barcode; // codigo de barras

    private String unit; // kg, g, l etc

    private Long quant;

    private Long quantMinStock; //Quantidade minima em estoque

    private BigDecimal valueBuy; // valor de compra

    private BigDecimal valueSell; // valor de venda

    private Date dateLastBuy; //Data da ultima compra do produto

    private String observation;

    private StatusValue status;

    private Date dateRegister; //Data de cadastro

    private Date dateUpdate; //Data de atualizacao

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier; //fornecedor

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category; //categoria

}

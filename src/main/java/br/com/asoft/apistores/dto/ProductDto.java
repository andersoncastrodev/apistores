package br.com.asoft.apistores.dto;

import br.com.asoft.apistores.enums.StatusValue;
import br.com.asoft.apistores.model.Category;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductDto {

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

    private SupplierDto supplier; //fornecedor

    private Category category; //categoria
}

package br.com.asoft.apistores.inp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInp {

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valorCompra;

    @NotNull
    private BigDecimal valorVenda;

    @NotNull
    private FornecedorIdInp fornecedor;

}

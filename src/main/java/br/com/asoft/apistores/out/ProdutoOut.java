package br.com.asoft.apistores.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoOut {

    private Long id;
    private String descricao;
    private String valorCompra;
    private String valorVenda;

}

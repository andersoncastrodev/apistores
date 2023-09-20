package br.com.asoft.apistores.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorOut {

    private Long id;
    private String nome;
    private String nomeFantasia;
    private String cpfcnpj;
}

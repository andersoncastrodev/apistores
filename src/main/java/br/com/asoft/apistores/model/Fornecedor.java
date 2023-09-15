package br.com.asoft.apistores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Fornecedor {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String nomeFantasia;

    private String cpfcnpj;
}

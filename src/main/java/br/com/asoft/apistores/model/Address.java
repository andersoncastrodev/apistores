package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.StateValue;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String street; //Rua, Logradouro

    private String numbers;

    private String neighborhood; //Bairro

    private String city; //Cidade

    @Enumerated(EnumType.STRING) // Para salvar a Constante do Enum
    private StateValue state; //Estado

    private String cep;

    private String complement;

    private String typeAddress; //Residencial, Comercial

}

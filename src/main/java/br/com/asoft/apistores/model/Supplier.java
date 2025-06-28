package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.GenderValue;
import br.com.asoft.apistores.enums.StatusValue;
import br.com.asoft.apistores.enums.TypePerson;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "supplier")
public class Supplier { // Fornecedor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private TypePerson typePerson;

    private String cpfCnpj;

    private String rgIe;

    private String name;

    private GenderValue gender;//Genero

    private String nameFantasy;

    private String telephoneFirst;

    private String telephoneSecond;

    private String email;

    private LocalDateTime dateRegister;

    private StatusValue status;

    private String observation;


    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;
}

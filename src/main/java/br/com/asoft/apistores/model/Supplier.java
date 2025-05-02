package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.TypePerson;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private TypePerson typePerson;

    private String cpfCnpj;

    private String rgIe;

    private String name;

    private String nameFantasy;

    private String phoneNumber;

    private String email;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;
}

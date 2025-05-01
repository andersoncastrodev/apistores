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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private TypePerson typePerson;

    private String cpfOrCnpj;

    private String rgOrIe;

    private String name;

    private String phoneNumber;

    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;



}

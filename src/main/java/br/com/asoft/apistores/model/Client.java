package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.TypePerson;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    private String cpfCnpj;

    private String rgIe;

    private String name;

    private String nameFantasy;

    private LocalDate dateBirth;

    private String phoneNumber;

    private String email;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

}

package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.GenderValue;
import br.com.asoft.apistores.enums.StatusValue;
import br.com.asoft.apistores.enums.TypePerson;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING) // Para salvar a Constante do Enum 'ATIVO' ou 'INATIVO'
    private TypePerson typePerson;

    private String cpfCnpj;

    private String rgIe;

    private String name;

    @Enumerated(EnumType.STRING) // Para salvar a Constante do Enum 'MASCULINO' ou 'FEMININO'
    private GenderValue gender;//Genero

    private LocalDate dateBirth;

    private String telephoneFirst;

    private String telephoneSecond;

    private String email;

    private String observation;

    @Enumerated(EnumType.STRING) // Para salvar a Constante do Enum 'ATIVO' ou 'INATIVO'
    private StatusValue status;

    private LocalDateTime dateRegister;

    private LocalDateTime dateUpdate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) //Para deletar o endereco junto com o cliente
    @JoinColumn(name = "id_address")
    private Address address;

}

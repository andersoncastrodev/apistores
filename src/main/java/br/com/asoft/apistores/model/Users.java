package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.GenderValue;
import br.com.asoft.apistores.enums.StatusValue;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class Users {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)// Para salvar a Constante do Enum 'MASCULINO' ou 'FEMININO'
    private GenderValue gender;//Genero

    private LocalDate dateBirth;

    private String email;

    private String telephoneFirst;

    private String telephoneSecond;

    private String cpf;

    private String login;

    private String password;

    private LocalDateTime dateRegister;

    @Enumerated(EnumType.STRING) // Para salvar a Constante do Enum 'ATIVO' ou 'INATIVO'
    private StatusValue status;

    private String observation;

    //@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_users"),
            inverseJoinColumns = @JoinColumn(name = "id_roles")
    )
    private Set<Roles> roles; // roles = papéis, são as premissões


    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

}

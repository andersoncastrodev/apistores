package br.com.asoft.apistores.dto;

import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.model.Roles;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UsersDto {

    private Long id;

    private String name;

    private LocalDate dateBirth;

    private String email;

    private String phone;

    private String cpf;

    private String login;

    private String password;

    private Set<Roles> roles; // roles = papéis, são as premissões

    private Address address;
}

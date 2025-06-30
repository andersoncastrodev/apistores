package br.com.asoft.apistores.dto;

import br.com.asoft.apistores.enums.GenderValue;
import br.com.asoft.apistores.enums.StatusValue;
import br.com.asoft.apistores.enums.TypePerson;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Classe de Dto = Objeto de Transferência de Dados (do inglês, Data Transfer Object),
 * No projeto vou usar O mesmo Dto tanto para entra de dados com a saida de dados.
 *
 * 'UsersRequest ou Inp' = Dados de Entrada e 'UsersDto ou Out' = Dados de saida.
 *
 * Para o projeto não repetir muito codigo desnecessários.
 */
@Getter
@Setter
public class ClientDto {

    private Long id;

    private TypePerson typePerson;

    private String cpfCnpj;

    private String rgIe;

    private String name;

    private GenderValue gender;//Genero

    private LocalDate dateBirth;

    private String telephoneFirst;

    private String telephoneSecond;

    private String email;

    private LocalDateTime dateRegister;

    private StatusValue status;

    private String observation;

    private AddressDto address;
}

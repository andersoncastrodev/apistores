package br.com.asoft.apistores.dto;

import br.com.asoft.apistores.enums.StatusValue;
import br.com.asoft.apistores.enums.TypePerson;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SupplierDto {

    private Long id;

    private TypePerson typePerson;

    private String cpfCnpj;

    private String rgIe;

    private String name;

    private String telephoneFirst;

    private String telephoneSecond;

    private String email;

    private String observation;

    private StatusValue status;

    private LocalDateTime dateRegister;

    private LocalDateTime dateUpdate;

    private AddressDto address;
}

package br.com.asoft.apistores.controller;


import br.com.asoft.apistores.enums.GenderValue;
import br.com.asoft.apistores.enums.StatusValue;
import br.com.asoft.apistores.enums.TypePayment;
import br.com.asoft.apistores.enums.TypePerson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enum")
@RequiredArgsConstructor
public class EnumValuesController {

    @GetMapping("/status")

    public List<Map<String, String>> getStatusValues() {
        return Arrays.stream(StatusValue.values())
                .map(status -> Map.of("value", status.name(), "label", status.getLabel()))
                .toList();
    }

    @GetMapping("/gender")
    public List<Map<String, String>> getGenderValues() {
        return Arrays.stream(GenderValue.values())
                .map(gen -> Map.of("value", gen.name(), "label", gen.getLabel()))
                .toList();
    }

    @GetMapping("/type-payment")
    public List<Map<String, String>> getPaymentTypeValues() {
        return Arrays.stream(TypePayment.values())
                .map(payment -> Map.of("value", payment.name(), "label", payment.getLabel()))
                .toList();
    }

    @GetMapping("/type-person")
    public List<Map<String, String>> getTypePerson() {
        return Arrays.stream(TypePerson.values())
                .map(person -> Map.of("value", person.name(), "label", person.getLabel()))
                .toList();
    }

//    @GetMapping("/roles")
//    public List<Map<String, String>> getRolesValues() {
//        return Arrays.stream(RolesValue.values())
//                .map(roles -> Map.of("value", roles.name(), "label", roles.getLabel()))
//                .toList();
//    }


}

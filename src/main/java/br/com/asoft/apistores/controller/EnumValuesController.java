package br.com.asoft.apistores.controller;


import br.com.asoft.apistores.enums.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enum-values")
@RequiredArgsConstructor
public class EnumValuesController {


    @GetMapping("/status")
//    public List<Map<String, String>> getStatusValues() {
//
//        StatusValue[] values = StatusValue.values(); // Lista de Enuns
//
//        List<Map<String, String>> result = new ArrayList<>();
//
//        for (StatusValue value : values) {
//            Map<String, String> map = new HashMap<>(); // Cria um Map
//            map.put( value.name(), value.getLabel() ); // Adiciona a chave e o valor
//            result.add(map); // Adiciona o Map na Lista
//        }
//
//        return result;
//    }
//    public List<Map<String, String>> getStatusValues() {
//        return Arrays.stream(StatusValue.values())
//                .map(value -> {
//                    Map<String, String> map = new HashMap<>();
//                    map.put(value.name(), value.getLabel());
//                    return map;
//                })
//                .collect(Collectors.toList());
//    }

    public List<Map<String, String>> getStatusValues() {
        return Arrays.stream(StatusValue.values())
                .map(status -> Map.of("value", status.name(), "label", status.getLabel()))
                .toList();
    }

    @GetMapping("/sex")
    public List<Map<String, String>> getSexValues() {
        return Arrays.stream(SexValue.values())
                .map(sex -> Map.of("value", sex.name(), "label", sex.getLabel()))
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

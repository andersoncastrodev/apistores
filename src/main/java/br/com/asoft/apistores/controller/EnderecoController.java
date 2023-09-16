package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> buscaTodos(){
        return enderecoService.allEndereco();
    }
}

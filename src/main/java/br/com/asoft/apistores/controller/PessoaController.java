package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listaTodas(){
        return pessoaService.allPessoas();
    }

}

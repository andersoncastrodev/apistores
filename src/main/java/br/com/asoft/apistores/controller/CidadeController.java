package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping
    public List<Cidade> buscarTodas(){
        return cidadeService.allCidades();
    }
}

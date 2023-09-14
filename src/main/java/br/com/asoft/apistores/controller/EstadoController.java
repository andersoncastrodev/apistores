package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping()
    public List<Estado> buscaTodos(){
        return estadoService.allEstados();
    }
}

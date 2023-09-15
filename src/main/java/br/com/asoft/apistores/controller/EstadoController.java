package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.service.EstadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estados")
@RequiredArgsConstructor
@Slf4j
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping()
    public List<Estado> buscaTodos(){

        log.info("INFORMAÇÃO {}","Todos os Estados");

        log.error("ERROR {}","Valor ou Objeto");
        log.warn("PERIGO {}","Valor ou Objeto");
        log.debug("GEBUG {}","Valor ou Objeto");


        return estadoService.allEstados();
    }
}

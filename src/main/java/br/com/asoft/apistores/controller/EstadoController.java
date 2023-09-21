package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.EstadoMapper;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.out.EstadoOut;
import br.com.asoft.apistores.service.EstadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estados")
@RequiredArgsConstructor
@Slf4j
public class EstadoController {

    private final EstadoService estadoService;

    private final EstadoMapper estadoMapper;

    @GetMapping()
    public List<EstadoOut> buscaTodos(){
        List<EstadoOut> estados = estadoMapper.toListEstadoOut( estadoService.allEstados());
        log.info("INFORMATION:{}","Consulta da Todos os Estados");
        return estados;
    }

    @GetMapping("/{id}")
    public EstadoOut buscaPorId(@PathVariable Long id){

        return null;
    }
}

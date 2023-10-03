package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.EstadoInp;
import br.com.asoft.apistores.mapper.EstadoMapper;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.out.EstadoOut;
import br.com.asoft.apistores.service.EstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        EstadoOut estadoOut = estadoMapper.toEstadoOut( estadoService.findId(id));
        return estadoOut;
    }

    @PostMapping
    public EstadoOut salvarEstado(@RequestBody @Valid EstadoInp estadoInp){

        Estado estado = estadoMapper.toEstado(estadoInp);

        EstadoOut estadoOut = estadoMapper.toEstadoOut(estadoService.saveEstado(estado));

        return estadoOut;
    }

    @PutMapping("/{id}")
    public EstadoOut atualizarEstado(@RequestBody @Valid EstadoInp estadoInp, @PathVariable Long id){

        Estado estadoAtual = estadoService.findId(id);

        Estado estadoNovo = estadoMapper.copyToEstado(estadoInp,estadoAtual);

        return estadoMapper.toEstadoOut( estadoService.saveEstado(estadoNovo));
    }

    @DeleteMapping("{id}")
    public void ExcluirEstado(@PathVariable Long id){
        estadoService.deleteEstado(id);
    }


}

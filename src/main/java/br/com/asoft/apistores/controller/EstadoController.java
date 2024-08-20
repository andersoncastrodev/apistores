package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.EstadoFilter;
import br.com.asoft.apistores.inp.EstadoInp;
import br.com.asoft.apistores.mapper.EstadoMapper;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.out.EstadoOut;
import br.com.asoft.apistores.service.EstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("estados")
@RequiredArgsConstructor
@Slf4j
public class EstadoController {

    private final EstadoService estadoService;

    private final EstadoMapper estadoMapper;

    @GetMapping
    public Page<EstadoOut> buscaTodos(EstadoFilter estadoFilter, Pageable pageable) {

        Page<Estado> estadoPage = estadoService.allEstadosPage(estadoFilter, pageable);

        List<EstadoOut> estadoOutsList = estadoMapper.toListEstadoOut(estadoPage.getContent());

        Page<EstadoOut> estadoOutPage = new PageImpl<>(estadoOutsList,pageable,estadoPage.getTotalElements());

        return estadoOutPage;
    }
//    @GetMapping()
//    public List<EstadoOut> buscaTodos(){
//        List<EstadoOut> estados = estadoMapper.toListEstadoOut( estadoService.allEstados());
//        log.info("INFORMATION:{}","Consulta da Todos os Estados");
//        return estados;
//    }

//    @GetMapping("/{id}")
//    public EstadoOut buscaPorId(@PathVariable Long id) {
//        EstadoOut estadoOut = estadoMapper.toEstadoOut( estadoService.findId(id));
//        return estadoOut;
//    }

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
    public void excluirEstado(@PathVariable Long id){
        estadoService.deleteEstado(id);
    }

    @GetMapping("/relatorioestados")
    public ResponseEntity<InputStreamResource> relatorioEstados() {

        try {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=estados.pdf");

        InputStreamResource relatorio = new InputStreamResource(estadoService.relatorioEstado());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}

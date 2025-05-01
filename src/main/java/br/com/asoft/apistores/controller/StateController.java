package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.StateFilter;
import br.com.asoft.apistores.inp.EstadoInp;
import br.com.asoft.apistores.mapper.StateMapper;
import br.com.asoft.apistores.model.State;
import br.com.asoft.apistores.out.EstadoOut;
import br.com.asoft.apistores.service.StateService;
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
public class StateController {

    private final StateService stateService;

    private final StateMapper stateMapper;

    @GetMapping
    public Page<EstadoOut> buscaTodos(StateFilter stateFilter, Pageable pageable) {

        Page<State> estadoPage = stateService.allEstadosPage(stateFilter, pageable);

        List<EstadoOut> estadoOutsList = stateMapper.toListEstadoOut(estadoPage.getContent());

        Page<EstadoOut> estadoOutPage = new PageImpl<>(estadoOutsList,pageable,estadoPage.getTotalElements());

        return estadoOutPage;
    }
//    @GetMapping()
//    public List<EstadoOut> buscaTodos(){
//        List<EstadoOut> estados = stateMapper.toListEstadoOut( stateService.allEstados());
//        log.info("INFORMATION:{}","Consulta da Todos os Estados");
//        return estados;
//    }

    @GetMapping("/{id}")
    public EstadoOut buscaPorId(@PathVariable Long id) {
        EstadoOut estadoOut = stateMapper.toEstadoOut( stateService.findId(id));
        return estadoOut;
    }

    @PostMapping
    public EstadoOut salvarEstado(@RequestBody @Valid EstadoInp estadoInp){

        State state = stateMapper.toEstado(estadoInp);

        EstadoOut estadoOut = stateMapper.toEstadoOut(stateService.saveEstado(state));

        return estadoOut;
    }

    @PutMapping("/{id}")
    public EstadoOut atualizarEstado(@RequestBody @Valid EstadoInp estadoInp, @PathVariable Long id){

        State stateAtual = stateService.findId(id);

        State stateNovo = stateMapper.copyToEstado(estadoInp, stateAtual);

        return stateMapper.toEstadoOut( stateService.saveEstado(stateNovo));
    }

    @DeleteMapping("{id}")
    public void excluirEstado(@PathVariable Long id){
        stateService.deleteEstado(id);
    }

//    @GetMapping("/relatorioestados")
//    public ResponseEntity<InputStreamResource> relatorioEstados() {
//
//        try {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=estados.pdf");
//
//        InputStreamResource relatorio = new InputStreamResource(stateService.relatorioEstado());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(relatorio);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }


}

package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.CidadeFilter;
import br.com.asoft.apistores.inp.CidadeInp;
import br.com.asoft.apistores.mapper.CidadeMapper;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.out.CidadeOut;
import br.com.asoft.apistores.service.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    private final CidadeMapper cidadeMapper;

    @GetMapping
    public Page<CidadeOut> buscarTodas(CidadeFilter cidadeFilter, Pageable pageable) {

        Page<Cidade> cidadesPage = cidadeService.allCidadesPage(cidadeFilter, pageable);

        List<CidadeOut> cidadesOutList = cidadeMapper.toListCidadeOut(cidadesPage.getContent());

        Page<CidadeOut> cidadesPageOut = new PageImpl<>(cidadesOutList,pageable,cidadesPage.getTotalElements());

        return cidadesPageOut;
    }

    // SEM PAGINAÇÃO
//    @GetMapping
//    public List<CidadeOut> buscarTodas(){
//        List<CidadeOut> cidades = cidadeMapper.toListCidadeOut(cidadeService.allCidades());
//        return cidades;
//    }

    @GetMapping("/{id}")
    public CidadeOut buscarPorId(@PathVariable Long id){
        return cidadeMapper.toCidadeOut(cidadeService.findId(id));
    }

    @PostMapping
    public CidadeOut salvarCidade(@RequestBody @Valid CidadeInp cidadeInp) {

        Cidade cidade = cidadeMapper.toCidade(cidadeInp);

        CidadeOut cidadeOut = cidadeMapper.toCidadeOut( cidadeService.saveCidade(cidade));

        return cidadeOut;

    }

    @PutMapping("/{id}")
    public CidadeOut atualizarCidade(@RequestBody @Valid CidadeInp cidadeInp, @PathVariable Long id) {

        Cidade cidadeAtual = cidadeService.findId(id);

        Cidade cidadeNova = cidadeMapper.copyToCidade(cidadeInp,cidadeAtual);

        return cidadeMapper.toCidadeOut(cidadeService.saveCidade(cidadeNova));
    }

    @DeleteMapping("/{id}")
    public void excluirCidade(@PathVariable Long id){

        cidadeService.deletarCidade(id);
    }

    @GetMapping("/relatoriocidades")
    public ResponseEntity<InputStreamResource> relatorioCidades() {

        try {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pessoas.pdf");

        InputStreamResource relatorio = new InputStreamResource(cidadeService.relatorioCidade());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

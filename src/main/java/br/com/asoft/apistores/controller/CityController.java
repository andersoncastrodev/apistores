package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.CityFilter;
import br.com.asoft.apistores.inp.CidadeInp;
import br.com.asoft.apistores.mapper.CityMapper;
import br.com.asoft.apistores.model.City;
import br.com.asoft.apistores.out.CidadeOut;
import br.com.asoft.apistores.service.CityService;
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
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    private final CityMapper cityMapper;

    @GetMapping
    public Page<CidadeOut> buscarTodas(CityFilter cityFilter, Pageable pageable) {

        Page<City> cidadesPage = cityService.allCidadesPage(cityFilter, pageable);

        List<CidadeOut> cidadesOutList = cityMapper.toListCidadeOut(cidadesPage.getContent());

        Page<CidadeOut> cidadesPageOut = new PageImpl<>(cidadesOutList,pageable,cidadesPage.getTotalElements());

        return cidadesPageOut;
    }

    // SEM PAGINAÇÃO
//    @GetMapping
//    public List<CidadeOut> buscarTodas(){
//        List<CidadeOut> cidades = cityMapper.toListCidadeOut(cityService.allCidades());
//        return cidades;
//    }

    @GetMapping("/{id}")
    public CidadeOut buscarPorId(@PathVariable Long id){
        return cityMapper.toCidadeOut(cityService.findId(id));
    }

    @PostMapping
    public CidadeOut salvarCidade(@RequestBody @Valid CidadeInp cidadeInp) {

        City city = cityMapper.toCidade(cidadeInp);

        CidadeOut cidadeOut = cityMapper.toCidadeOut( cityService.saveCidade(city));

        return cidadeOut;

    }

//    @PutMapping("/{id}")
//    public CidadeOut atualizarCidade(@RequestBody @Valid CidadeInp cidadeInp, @PathVariable Long id) {
//
//        City cidadeAtual = cityService.findId(id);
//
//        City cidadeNova = cityMapper.copyToCidade(cidadeInp,cidadeAtual);
//
//        return cityMapper.toCidadeOut(cityService.saveCidade(cidadeNova));
//    }

    @DeleteMapping("/{id}")
    public void excluirCidade(@PathVariable Long id){

        cityService.deletarCidade(id);
    }

//    @GetMapping("/relatoriocidades")
//    public ResponseEntity<InputStreamResource> relatorioCidades() {
//
//        try {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=pessoas.pdf");
//
//        InputStreamResource relatorio = new InputStreamResource(cityService.relatorioCidade());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(relatorio);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }

}

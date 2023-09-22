package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.CidadeMapper;
import br.com.asoft.apistores.out.CidadeOut;
import br.com.asoft.apistores.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;

    private final CidadeMapper cidadeMapper;

    @GetMapping
    public List<CidadeOut> buscarTodas(){

        List<CidadeOut> cidades = cidadeMapper.toListCidadeOut(cidadeService.allCidades());

        return cidades;
    }

    @GetMapping("/{id}")
    public CidadeOut buscarPorId(@PathVariable Long id){
        return cidadeMapper.toCidadeOut(cidadeService.findId(id));
    }


}

package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.CidadeInp;
import br.com.asoft.apistores.mapper.CidadeMapper;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.out.CidadeOut;
import br.com.asoft.apistores.service.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public CidadeOut salvarCidade(@RequestBody @Valid CidadeInp cidadeInp){

        Cidade cidade = cidadeMapper.toCidade(cidadeInp);

        CidadeOut cidadeOut = cidadeMapper.toCidadeOut( cidadeService.saveCidade(cidade));

        return cidadeOut;

    }

    @PutMapping("/{id}")
    public CidadeOut atualizarCidade(@RequestBody @Valid CidadeInp cidadeInp, @PathVariable Long id){

        Cidade cidadeAtual = cidadeService.findId(id);

        Cidade cidadeNova = cidadeMapper.copyToCidade(cidadeInp,cidadeAtual);

        return cidadeMapper.toCidadeOut(cidadeService.saveCidade(cidadeNova));
    }

    @DeleteMapping("/{id}")
    public void excluirCidade(@PathVariable Long id){

        cidadeService.deletarCidade(id);
    }


}

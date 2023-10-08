package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.PessoaInp;
import br.com.asoft.apistores.mapper.PessoaMapper;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.out.PessoaOut;
import br.com.asoft.apistores.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    private final PessoaMapper pessoaMapper;

    @GetMapping
    public List<PessoaOut> listaTodas(){
        List<PessoaOut> pessoas = pessoaMapper.toListPessoaOut(pessoaService.allPessoas());
        return pessoas;
    }

    @GetMapping("/{id}")
    public PessoaOut buscaPorId(@PathVariable Long id){
        PessoaOut pessoaOut = pessoaMapper.toPessoaOut(pessoaService.findId(id));
        return pessoaOut;
    }

    @PostMapping
    public PessoaOut salvarPessoa(@RequestBody @Valid PessoaInp pessoaInp){

        return pessoaMapper.toPessoaOut( pessoaService.savePessoa(pessoaMapper.toPessoa(pessoaInp)));
    }

    @PutMapping("/{id}")
    public PessoaOut alterarPessoa(@RequestBody PessoaInp pessoaInp, @PathVariable Long id){

        Pessoa pessoaAtual = pessoaService.findId(id);

        Pessoa pessoaNova = pessoaMapper.copyToPessoa(pessoaInp,pessoaAtual);

        return pessoaMapper.toPessoaOut(pessoaService.savePessoa(pessoaNova));
    }

    @DeleteMapping("/{id}")
    public void excluirPessoa(@PathVariable Long id){
        pessoaService.deletePessoa(id);
    }


}

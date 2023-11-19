package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.PessoaInp;
import br.com.asoft.apistores.mapper.PessoaMapper;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.out.PessoaOut;
import br.com.asoft.apistores.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/relatoriopessoas")
    public ResponseEntity<InputStreamResource> relatorioPessoas() {

        try {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pessoas.pdf");

        InputStreamResource relatorio = new InputStreamResource( pessoaService.relatorioTodasPessoas());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


}

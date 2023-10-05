package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.EnderecoIdInp;
import br.com.asoft.apistores.inp.EnderecoInp;
import br.com.asoft.apistores.mapper.EnderecoMapper;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.out.EnderecoOut;
import br.com.asoft.apistores.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    private final EnderecoMapper enderecoMapper;

    @GetMapping
    public List<EnderecoOut> buscaTodos(){
        List<EnderecoOut> enderecos = enderecoMapper.toListEnderecoOut(enderecoService.allEndereco());
        return enderecos;
    }

    @GetMapping("/{id}")
    public EnderecoOut buscaPorId(@PathVariable Long id){
        return enderecoMapper.toEnderecoOut(enderecoService.findId(id));
    }

    @PostMapping
    public EnderecoOut salvarEndereco(@RequestBody @Valid EnderecoInp enderecoInp){

          Endereco endereco = enderecoMapper.toEndereco(enderecoInp);

          EnderecoOut enderecoOut = enderecoMapper.toEnderecoOut( enderecoService.saveEndereco(endereco) );

          return enderecoOut;
    }

    @PutMapping("/{id}")
    public EnderecoOut atualizarEndereco(@RequestBody @Valid EnderecoInp enderecoInp, @PathVariable Long id){

        Endereco enderecoAtual = enderecoService.findId(id);

        Endereco enderecoNovo = enderecoMapper.copyToEndereco(enderecoInp,enderecoAtual);

        return enderecoMapper.toEnderecoOut(enderecoService.saveEndereco(enderecoNovo));
    }




}

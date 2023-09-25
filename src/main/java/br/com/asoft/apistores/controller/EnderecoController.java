package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.EnderecoMapper;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.out.EnderecoOut;
import br.com.asoft.apistores.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}

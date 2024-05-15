package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.EnderecoFilter;
import br.com.asoft.apistores.inp.EnderecoInp;
import br.com.asoft.apistores.mapper.EnderecoMapper;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.out.EnderecoOut;
import br.com.asoft.apistores.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    private final EnderecoMapper enderecoMapper;

    @GetMapping
    public Page<EnderecoOut> buscaTodos(EnderecoFilter enderecoFilter, Pageable pageable) {

        Page<Endereco> enderecoPage = enderecoService.allEnderecoPage(pageable);

        List<EnderecoOut> enderecoOutList = enderecoMapper.toListEnderecoOut(enderecoPage.getContent());

        Page<EnderecoOut> enderecoPageOuts = new PageImpl<>(enderecoOutList,pageable,enderecoPage.getTotalPages());

        return enderecoPageOuts;
    }
//    @GetMapping
//    public List<EnderecoOut> buscaTodos() {
//        List<EnderecoOut> enderecos = enderecoMapper.toListEnderecoOut(enderecoService.allEndereco());
//        return enderecos;
//    }

    @GetMapping("/{id}")
    public EnderecoOut buscaPorId(@PathVariable Long id) {
        return enderecoMapper.toEnderecoOut(enderecoService.findId(id));
    }

    @PostMapping
    public EnderecoOut salvarEndereco(@RequestBody @Valid EnderecoInp enderecoInp) {

        Endereco endereco = enderecoMapper.toEndereco(enderecoInp);

        EnderecoOut enderecoOut = enderecoMapper.toEnderecoOut(enderecoService.saveEndereco(endereco));

        return enderecoOut;
    }

    @PutMapping("/{id}")
    public EnderecoOut atualizarEndereco(@RequestBody @Valid EnderecoInp enderecoInp, @PathVariable Long id) {

        Endereco enderecoAtual = enderecoService.findId(id);

        Endereco enderecoNovo = enderecoMapper.copyToEndereco(enderecoInp, enderecoAtual);

        return enderecoMapper.toEnderecoOut(enderecoService.saveEndereco(enderecoNovo));
    }

    @DeleteMapping("/{id}")
    public void excluirEndereco(@PathVariable Long id) {

        enderecoService.deleteEndereco(id);

    }

    @GetMapping("/relatorioendereco")
    public ResponseEntity<InputStreamResource> relatorioEndereco() {

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=endereco.pdf");


            InputStreamResource relatorio = new InputStreamResource(enderecoService.relatorioEndereco());


        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}

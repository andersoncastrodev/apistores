package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.FornecedorInp;
import br.com.asoft.apistores.mapper.FornecedorMapper;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.out.FornecedorOut;
import br.com.asoft.apistores.service.FornecedorService;
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
@RequestMapping("fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    private final FornecedorMapper fornecedorMapper;

    @GetMapping
    public Page<FornecedorOut> buscaTodos(Pageable pageable){

        Page<Fornecedor> fornecedorPage = fornecedorService.allTodosPage(pageable);

        List<FornecedorOut> fornecedorOutList = fornecedorMapper.toListFornecedorOut(fornecedorPage.getContent());

        Page<FornecedorOut> fornecedorOutPage = new PageImpl<>(fornecedorOutList,pageable,fornecedorPage.getTotalPages());

        return fornecedorOutPage;
    }
//    @GetMapping
//    public List<FornecedorOut> buscaTodos(){
//        List<FornecedorOut> fornecedores = fornecedorMapper.toListFornecedorOut(fornecedorService.allTodos());
//        return fornecedores;
//    }

    @GetMapping("/{id}")
    public FornecedorOut buscaPorId(@PathVariable Long id) {
        FornecedorOut fornecedorOut = fornecedorMapper.toFornecedorOut(fornecedorService.findId(id));
        return fornecedorOut;
    }

    @PostMapping
    public FornecedorOut salvarFornecedor(@RequestBody @Valid FornecedorInp fornecedorInp){
        return fornecedorMapper.toFornecedorOut( fornecedorService.saveFonecedor(fornecedorMapper.toFornecedor(fornecedorInp)));
    }

    @PutMapping("/{id}")
    public FornecedorOut alterarFornecedor(@RequestBody @Valid FornecedorInp fornecedorInp, @PathVariable Long id){

        Fornecedor fornecedorAtual = fornecedorService.findId(id);

        Fornecedor fornecedorNovo = fornecedorMapper.copyToFornecedor(fornecedorInp, fornecedorAtual);

        return fornecedorMapper.toFornecedorOut(fornecedorService.saveFonecedor(fornecedorNovo));
    }

    @DeleteMapping("/{id}")
    public void excluirFornecedor(@PathVariable Long id){
        fornecedorService.deletarFornecedor(id);
    }

    @GetMapping("/relatoriofornecedor")
    public ResponseEntity<InputStreamResource> relatorioFornecedores() {

        try {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=fornecedores.pdf");

        InputStreamResource relatorio = new InputStreamResource(fornecedorService.relatorioFornecedor());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }




}

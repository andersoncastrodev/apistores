package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.FornecedorInp;
import br.com.asoft.apistores.inp.ItemVendaInp;
import br.com.asoft.apistores.mapper.ItemVendaMapper;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.model.ItemVenda;
import br.com.asoft.apistores.out.ItemVendaOut;
import br.com.asoft.apistores.service.ItemVendaService;
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
@RequestMapping("/itenvendas")
@RequiredArgsConstructor
public class ItemVendaController {

    private final ItemVendaService itemVendaService;

    private final ItemVendaMapper itemVendaMapper;

    @GetMapping
    public List<ItemVendaOut> todasItenVendas() {
        return itemVendaMapper.toListItemVendaOut(itemVendaService.allTodos());
    }

    @GetMapping("/{id}")
    public ItemVendaOut buscaIdVenda(@PathVariable Long id) {
        return itemVendaMapper.toItenVendaOut(itemVendaService.findId(id));
    }

    @PostMapping
    public ItemVendaOut salvarItemVenda(@RequestBody ItemVendaInp itenVendaInp) {
        ItemVenda itemVenda = itemVendaService.saveItemVenda( itemVendaMapper.toItenVenda(itenVendaInp));
        return itemVendaMapper.toItenVendaOut(itemVenda);

    }

    @PutMapping("/{id}")
    public ItemVendaOut alterarItemVenda(@RequestBody @Valid ItemVendaInp itemVendaInp, @PathVariable Long id) {

        ItemVenda itemVendaAtual = itemVendaService.findId(id);

        ItemVenda itemVendaNovo = itemVendaMapper.copyToItemVenda(itemVendaInp, itemVendaAtual);

        return itemVendaMapper.toItenVendaOut(itemVendaService.saveItemVenda(itemVendaNovo));
    }

    @DeleteMapping("/{id}")
    public void excluirItemVenda(@PathVariable Long id) {
        itemVendaService.deletarItemVenda(id);
    }


//    @GetMapping("/relatorioitemvendas")
//    public ResponseEntity<InputStreamResource> relatorioPessoas() {
//
//        try {
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "inline; filename=pessoas.pdf");
//
//            InputStreamResource relatorio = new InputStreamResource(itemVendaService.relatorioItemVenda());
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .body(relatorio);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }



}

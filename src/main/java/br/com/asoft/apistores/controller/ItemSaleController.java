package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.ItemSaleMapper;
import br.com.asoft.apistores.model.ItemSale;
import br.com.asoft.apistores.service.ItemSaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itenvendas")
@RequiredArgsConstructor
public class ItemSaleController {

    private final ItemSaleService itemSaleService;

    private final ItemSaleMapper itemSaleMapper;

//    @GetMapping
//    public Page<ItemVendaOut> todasItenVendas(Pageable pageable) {
//
//        Page<ItemSale> itemVendaPage = itemSaleService.allTodosPage(pageable);
//        List<ItemVendaOut> itemVendaOutsList = itemSaleMapper.toListItemVendaOut(itemVendaPage.getContent());
//        Page<ItemVendaOut> itemVendaOutPage = new PageImpl<>(itemVendaOutsList,pageable,itemVendaPage.getTotalPages());
//        return itemVendaOutPage;
//    }
//    @GetMapping
//    public List<ItemVendaOut> todasItenVendas() {
//        return itemSaleMapper.toListItemVendaOut(itemSaleService.allTodos());
//    }

///

//    @PostMapping
//    public ItemVendaOut salvarItemVenda(@RequestBody ItemVendaInp itenVendaInp) {
//        ItemSale itemVenda = itemSaleService.saveItemVenda( itemSaleMapper.toItenVenda(itenVendaInp));
//        return itemSaleMapper.toItenVendaOut(itemVenda);
//
//    }

//    @PutMapping("/{id}")
//    public ItemVendaOut alterarItemVenda(@RequestBody @Valid ItemVendaInp itemVendaInp, @PathVariable Long id) {
//
//        ItemSale itemSaleAtual = itemSaleService.findId(id);
//
//        ItemSale itemSaleNovo = itemSaleMapper.copyToItemVenda(itemVendaInp, itemSaleAtual);
//
//        return itemSaleMapper.toItenVendaOut(itemSaleService.saveItemVenda(itemSaleNovo));
//    }

    @DeleteMapping("/{id}")
    public void excluirItemVenda(@PathVariable Long id) {
        itemSaleService.deletarItemVenda(id);
    }


//    @GetMapping("/relatorioitemvendas")
//    public ResponseEntity<InputStreamResource> relatorioPessoas() {
//
//        try {
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "inline; filename=pessoas.pdf");
//
//            InputStreamResource relatorio = new InputStreamResource(itemSaleService.relatorioItemVenda());
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

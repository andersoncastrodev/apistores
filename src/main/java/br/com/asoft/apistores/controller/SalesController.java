package br.com.asoft.apistores.controller;


import br.com.asoft.apistores.mapper.SalesMapper;
import br.com.asoft.apistores.model.Sales;
import br.com.asoft.apistores.service.SalesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendas")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    private final SalesMapper salesMapper;

//    @GetMapping
//    public Page<VendaOut> listaVendas(Pageable pageable) {
//
//        Page<Sales> vendaPage = salesService.allTodas(pageable);
//
//        List<VendaOut> vendaOutsList = salesMapper.toListVendaOut(vendaPage.getContent());
//
//        Page<VendaOut> vendaOutPage = new PageImpl<>(vendaOutsList,pageable, vendaPage.getTotalElements());
//
//        return vendaOutPage;
//    }
//
////    @GetMapping
////    public List<VendaOut> listaVendas(){
////        return  salesMapper.toListVendaOut( salesService.allTodas());
////    }
//
//    @GetMapping("/{id}")
//    public VendaOut buscaPorId(@PathVariable Long id){
//        return salesMapper.toVendaOut( salesService.findId(id));
//    }
//
//    @PostMapping
//    public VendaOut salvaVenda(@RequestBody @Valid VendaInp vendaInp) {
//
//        Sales sales = salesService.saveVenda(salesMapper.toVenda(vendaInp));
//       return salesMapper.toVendaOut(sales);
//    }
//
//    @PutMapping("/{id}")
//    public VendaOut alterarVenda(@RequestBody VendaInp vendaInp, @PathVariable Long id){
//        Sales salesAtual = salesService.findId(id);
//
//        Sales salesNova = salesMapper.copyToVendaInp(vendaInp, salesAtual);
//
//        return salesMapper.toVendaOut(salesService.saveVenda(salesNova));
//    }
//    @DeleteMapping("/{id}")
//    public void excluirVenda(@PathVariable Long id){
//            salesService.deleteVenda(id);
//    }


//    @GetMapping("/relatoriovendas")
//    public ResponseEntity<InputStreamResource> relatorioPessoas() {
//
//        try {
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "inline; filename=vendas.pdf");
//
//            InputStreamResource relatorio = new InputStreamResource(salesService.relatorioTodasVendas());
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .body(relatorio);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}

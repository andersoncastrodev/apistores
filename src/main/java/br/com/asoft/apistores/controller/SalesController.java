package br.com.asoft.apistores.controller;


import br.com.asoft.apistores.inp.VendaInp;
import br.com.asoft.apistores.mapper.VendaMapper;
import br.com.asoft.apistores.model.Sales;
import br.com.asoft.apistores.out.VendaOut;
import br.com.asoft.apistores.service.VendaService;
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
@RequestMapping("vendas")
@RequiredArgsConstructor
public class SalesController {

    private final VendaService vendaService;

    private final VendaMapper vendaMapper;

    @GetMapping
    public Page<VendaOut> listaVendas(Pageable pageable) {

        Page<Sales> vendaPage = vendaService.allTodas(pageable);

        List<VendaOut> vendaOutsList = vendaMapper.toListVendaOut(vendaPage.getContent());

        Page<VendaOut> vendaOutPage = new PageImpl<>(vendaOutsList,pageable, vendaPage.getTotalElements());

        return vendaOutPage;
    }

//    @GetMapping
//    public List<VendaOut> listaVendas(){
//        return  vendaMapper.toListVendaOut( vendaService.allTodas());
//    }

    @GetMapping("/{id}")
    public VendaOut buscaPorId(@PathVariable Long id){
        return vendaMapper.toVendaOut( vendaService.findId(id));
    }

    @PostMapping
    public VendaOut salvaVenda(@RequestBody @Valid VendaInp vendaInp) {

        Sales sales = vendaService.saveVenda(vendaMapper.toVenda(vendaInp));
       return vendaMapper.toVendaOut(sales);
    }

    @PutMapping("/{id}")
    public VendaOut alterarVenda(@RequestBody VendaInp vendaInp, @PathVariable Long id){
        Sales salesAtual = vendaService.findId(id);

        Sales salesNova = vendaMapper.copyToVendaInp(vendaInp, salesAtual);

        return vendaMapper.toVendaOut(vendaService.saveVenda(salesNova));
    }
    @DeleteMapping("/{id}")
    public void excluirVenda(@PathVariable Long id){
            vendaService.deleteVenda(id);
    }


    @GetMapping("/relatoriovendas")
    public ResponseEntity<InputStreamResource> relatorioPessoas() {

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=vendas.pdf");

            InputStreamResource relatorio = new InputStreamResource(vendaService.relatorioTodasVendas());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

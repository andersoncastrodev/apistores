package br.com.asoft.apistores.controller;


import br.com.asoft.apistores.inp.VendaInp;
import br.com.asoft.apistores.mapper.VendaMapper;
import br.com.asoft.apistores.model.Venda;
import br.com.asoft.apistores.out.VendaOut;
import br.com.asoft.apistores.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
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
public class VendaController {

    private final VendaService vendaService;

    private final VendaMapper vendaMapper;

    @GetMapping
    public Page<VendaOut> listaVendas(Pageable pageable){



     //   return  vendaMapper.toListVendaOut( vendaService.allTodas());
        return null;
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
    public VendaOut salvaVenda(@RequestBody @Valid VendaInp vendaInp){

        Venda venda = vendaService.saveVenda(vendaMapper.toVenda(vendaInp));
       return vendaMapper.toVendaOut(venda);
    }

    @PutMapping("/{id}")
    public VendaOut alterarVenda(@RequestBody VendaInp vendaInp, @PathVariable Long id){
        Venda vendaAtual = vendaService.findId(id);

        Venda vendaNova = vendaMapper.copyToVendaInp(vendaInp, vendaAtual);

        return vendaMapper.toVendaOut(vendaService.saveVenda(vendaNova));
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

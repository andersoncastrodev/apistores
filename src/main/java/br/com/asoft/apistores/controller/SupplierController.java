package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.SupplierDto;
import br.com.asoft.apistores.mapper.SupplierMapper;
import br.com.asoft.apistores.service.SuppilerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SuppilerService suppilerService;

    private final SupplierMapper supplierMapper;

//    @GetMapping
//    public Page<FornecedorOut> buscaTodos(Pageable pageable){
//
//        Page<Supplier> fornecedorPage = suppilerService.allTodosPage(pageable);
//
//        List<FornecedorOut> fornecedorOutList = supplierMapper.toListFornecedorOut(fornecedorPage.getContent());
//
//        Page<FornecedorOut> fornecedorOutPage = new PageImpl<>(fornecedorOutList,pageable,fornecedorPage.getTotalPages());
//
//        return fornecedorOutPage;
//    }
////    @GetMapping
////    public List<FornecedorOut> buscaTodos(){
////        List<FornecedorOut> fornecedores = supplierMapper.toListFornecedorOut(suppilerService.allTodos());
////        return fornecedores;
////    }
//
//    @GetMapping("/{id}")
//    public FornecedorOut buscaPorId(@PathVariable Long id) {
//        FornecedorOut fornecedorOut = supplierMapper.toFornecedorOut(suppilerService.findId(id));
//        return fornecedorOut;
//    }
//
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDto createSupplier(@RequestBody @Valid SupplierDto supplierDto) {
        return suppilerService.saveSupplierWithAddress(supplierDto);
    }
//
//    @PutMapping("/{id}")
//    public FornecedorOut alterarFornecedor(@RequestBody @Valid FornecedorInp fornecedorInp, @PathVariable Long id){
//
//        Supplier supplierAtual = suppilerService.findId(id);
//
//        Supplier supplierNovo = supplierMapper.copyToFornecedor(fornecedorInp, supplierAtual);
//
//        return supplierMapper.toFornecedorOut(suppilerService.saveFonecedor(supplierNovo));
//    }
//
//    @DeleteMapping("/{id}")
//    public void excluirFornecedor(@PathVariable Long id){
//        suppilerService.deletarFornecedor(id);
//    }

//    @GetMapping("/relatoriofornecedor")
//    public ResponseEntity<InputStreamResource> relatorioFornecedores() {
//
//        try {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=fornecedores.pdf");
//
//        InputStreamResource relatorio = new InputStreamResource(suppilerService.relatorioFornecedor());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(relatorio);
//
//        } catch (IOException e) {
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }




}

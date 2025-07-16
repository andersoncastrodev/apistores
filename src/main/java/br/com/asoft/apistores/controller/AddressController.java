package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.AddressMapper;
import br.com.asoft.apistores.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    private final AddressMapper addressMapper;

//    @GetMapping
//    public Page<EnderecoOut> buscaTodos(AddressFilter addressFilter, Pageable pageable) {
//
//        Page<Address> enderecoPage = addressService.allEnderecoPage(addressFilter, pageable);
//
//        List<EnderecoOut> enderecoOutList = addressMapper.toListEnderecoOut(enderecoPage.getContent());
//
//        Page<EnderecoOut> enderecoPageOuts = new PageImpl<>(enderecoOutList,pageable,enderecoPage.getTotalPages());
//
//        return enderecoPageOuts;
//    }
////    @GetMapping
////    public List<EnderecoOut> buscaTodos() {
////        List<EnderecoOut> enderecos = addressMapper.toListEnderecoOut(addressService.allEndereco());
////        return enderecos;
////    }
////
//    @GetMapping("/{id}")
//    public EnderecoOut buscaPorId(@PathVariable Long id) {
//        return addressMapper.toEnderecoOut(addressService.findId(id));
//    }
//
//    @PostMapping
//    public EnderecoOut salvarEndereco(@RequestBody @Valid AddressInp addressInp) {
//
//        Address address = addressMapper.toEndereco(addressInp);
//
//        EnderecoOut enderecoOut = addressMapper.toEnderecoOut(addressService.saveEndereco(address));
//
//        return enderecoOut;
//    }
//
//    @PutMapping("/{id}")
//    public EnderecoOut atualizarEndereco(@RequestBody @Valid AddressInp addressInp, @PathVariable Long id) {
//
//        Address addressAtual = addressService.findId(id);
//
//        Address addressNovo = addressMapper.copyToEndereco(addressInp, addressAtual);
//
//        return addressMapper.toEnderecoOut(addressService.saveEndereco(addressNovo));
//    }
//
//    @DeleteMapping("/{id}")
//    public void excluirEndereco(@PathVariable Long id) {
//
//        addressService.deleteEndereco(id);
//
//    }

//    @GetMapping("/relatorioendereco")
//    public ResponseEntity<InputStreamResource> relatorioEndereco() {
//
//        try {
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "inline; filename=endereco.pdf");
//
//
//            InputStreamResource relatorio = new InputStreamResource(addressService.relatorioEndereco());
//
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(relatorio);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }


}

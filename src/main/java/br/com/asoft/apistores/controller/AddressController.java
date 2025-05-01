package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.AddressFilter;
import br.com.asoft.apistores.inp.EnderecoInp;
import br.com.asoft.apistores.mapper.AddressMapper;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.out.EnderecoOut;
import br.com.asoft.apistores.service.AddressService;
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
public class AddressController {

    private final AddressService addressService;

    private final AddressMapper addressMapper;

    @GetMapping
    public Page<EnderecoOut> buscaTodos(AddressFilter addressFilter, Pageable pageable) {

        Page<Address> enderecoPage = addressService.allEnderecoPage(addressFilter, pageable);

        List<EnderecoOut> enderecoOutList = addressMapper.toListEnderecoOut(enderecoPage.getContent());

        Page<EnderecoOut> enderecoPageOuts = new PageImpl<>(enderecoOutList,pageable,enderecoPage.getTotalPages());

        return enderecoPageOuts;
    }
//    @GetMapping
//    public List<EnderecoOut> buscaTodos() {
//        List<EnderecoOut> enderecos = addressMapper.toListEnderecoOut(addressService.allEndereco());
//        return enderecos;
//    }
//
    @GetMapping("/{id}")
    public EnderecoOut buscaPorId(@PathVariable Long id) {
        return addressMapper.toEnderecoOut(addressService.findId(id));
    }

    @PostMapping
    public EnderecoOut salvarEndereco(@RequestBody @Valid EnderecoInp enderecoInp) {

        Address address = addressMapper.toEndereco(enderecoInp);

        EnderecoOut enderecoOut = addressMapper.toEnderecoOut(addressService.saveEndereco(address));

        return enderecoOut;
    }

    @PutMapping("/{id}")
    public EnderecoOut atualizarEndereco(@RequestBody @Valid EnderecoInp enderecoInp, @PathVariable Long id) {

        Address addressAtual = addressService.findId(id);

        Address addressNovo = addressMapper.copyToEndereco(enderecoInp, addressAtual);

        return addressMapper.toEnderecoOut(addressService.saveEndereco(addressNovo));
    }

    @DeleteMapping("/{id}")
    public void excluirEndereco(@PathVariable Long id) {

        addressService.deleteEndereco(id);

    }

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

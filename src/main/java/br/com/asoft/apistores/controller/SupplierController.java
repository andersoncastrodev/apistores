package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.SupplierDto;
import br.com.asoft.apistores.dto.SupplierSimpleDto;
import br.com.asoft.apistores.filter.SupplierFilter;
import br.com.asoft.apistores.service.SuppilerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SuppilerService suppilerService;

    @GetMapping
    public Page<SupplierDto> findSupplierPage(SupplierFilter supplierFilter, Pageable pageable) {
        return suppilerService.findSupplierPage(supplierFilter, pageable);
    }

    @GetMapping("/simple")
    public List<SupplierSimpleDto> findSupplierSimple() {
        return suppilerService.findSupplierSimple();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDto createSupplier(@RequestBody @Valid SupplierDto supplierDto) {
        return suppilerService.saveSupplierWithAddress(supplierDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SupplierDto updateSupplier(@PathVariable Long id, @RequestBody @Valid SupplierDto supplierDto) {
        return suppilerService.updateSupplierWithAddress(id, supplierDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSupplier(@PathVariable Long id) {
        suppilerService.deleteSupplier(id);
    }

}

package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.SupplierDto;
import br.com.asoft.apistores.dto.SupplierSimpleDto;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.SupplierFilter;
import br.com.asoft.apistores.mapper.AddressMapper;
import br.com.asoft.apistores.mapper.SupplierMapper;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.model.Supplier;
import br.com.asoft.apistores.respository.SuppilerRepository;
import br.com.asoft.apistores.specifications.SupplierSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuppilerService {

    private final SuppilerRepository suppilerRepository;
    private final SupplierMapper supplierMapper;

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public Page<SupplierDto> findSupplierPage(SupplierFilter supplierFilter, Pageable pageable) {
        Page<Supplier> supplier = suppilerRepository.findAll(SupplierSpec.filter(supplierFilter), pageable);
        List<SupplierDto> listSupplierDto = supplierMapper.toListSupplierDto(supplier.getContent());//.getContent() Importante
        Page<SupplierDto> supplierDtoPage = new PageImpl<>(listSupplierDto,pageable,supplier.getTotalElements());// new PageImpl<> e .getTotalElements() Importante
        return supplierDtoPage;
    }

//    public List<SupplierDto> findSupplierAll() {
//       return supplierMapper.toListSupplierDto(suppilerRepository.findAll());
//    }

    public List<SupplierSimpleDto> findSupplierSimple() {
        return suppilerRepository.findAll()
                .stream()
                .map(supplier -> new SupplierSimpleDto(supplier.getId(), supplier.getName()))
                .collect(Collectors.toList());
    }

    public Supplier findId(Long id){
        return tryOrFail(id);
    }

    @Transactional
    public SupplierDto saveSupplierWithAddress(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toSupplier(supplierDto);
        if(supplierDto.getAddress() != null){
            Address address = addressService.saveAddress(addressMapper.toAddress(supplierDto.getAddress()));
            supplier.setAddress(address);
        }
        supplier.setDateRegister(LocalDateTime.now());
        return supplierMapper.toSupplierDto(suppilerRepository.save(supplier));
    }

    @Transactional
    public SupplierDto updateSupplierWithAddress(Long id, SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toSupplier(supplierDto);
        supplier.setId(id);
        if(supplierDto.getAddress() != null) {
            Address address = addressService.saveAddress(addressMapper.toAddress(supplierDto.getAddress()));
            supplier.setAddress(address);
        }
        supplier.setDateUpdate(LocalDateTime.now());
        return supplierMapper.toSupplierDto(suppilerRepository.save(supplier));
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = tryOrFail(id);
        suppilerRepository.delete(supplier);
    }

    public Supplier tryOrFail(Long id) {
        return suppilerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Supplier", id));
    }

}

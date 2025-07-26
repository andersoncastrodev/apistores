package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.SupplierDto;
import br.com.asoft.apistores.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toSupplier(SupplierDto supplierDto);
    SupplierDto toSupplierDto(Supplier supplier);
    @Mapping(target = "address", ignore = true)
    List<SupplierDto> toListSupplierDto(List<Supplier> suppliers);
}

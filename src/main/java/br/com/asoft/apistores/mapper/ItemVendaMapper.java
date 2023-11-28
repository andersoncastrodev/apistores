package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.model.ItenVenda;
import br.com.asoft.apistores.out.FornecedorOut;
import br.com.asoft.apistores.out.ItenVendaOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemVendaMapper {

    ItenVendaOut toItenVendaOut(ItenVenda itenVenda);

    List<ItenVendaOut> toListItemVendaOut(List<ItenVenda> itenVendas);

}

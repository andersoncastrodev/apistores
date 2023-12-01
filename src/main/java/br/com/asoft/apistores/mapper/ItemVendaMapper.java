package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.ItenVenda;
import br.com.asoft.apistores.out.ItemVendaOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemVendaMapper {

    ItemVendaOut toItenVendaOut(ItenVenda itenVenda);

    List<ItemVendaOut> toListItemVendaOut(List<ItenVenda> itenVendas);

}

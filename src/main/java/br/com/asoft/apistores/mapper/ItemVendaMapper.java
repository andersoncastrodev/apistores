package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.ItemVendaInp;
import br.com.asoft.apistores.model.ItemVenda;
import br.com.asoft.apistores.out.ItemVendaOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemVendaMapper {

    ItemVendaOut toItenVendaOut(ItemVenda itenVenda);

    ItemVenda toItenVenda(ItemVendaInp itenVendaInp);

    ItemVenda copyToItemVenda(ItemVendaInp itemVendaInp, @MappingTarget ItemVenda itemVenda);
    List<ItemVendaOut> toListItemVendaOut(List<ItemVenda> itenVendas);

}

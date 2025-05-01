package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.ItemVendaInp;
import br.com.asoft.apistores.model.ItemSale;
import br.com.asoft.apistores.out.ItemVendaOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemSaleMapper {

    ItemVendaOut toItenVendaOut(ItemSale itenVenda);

    ItemSale toItenVenda(ItemVendaInp itenVendaInp);

    ItemSale copyToItemVenda(ItemVendaInp itemVendaInp, @MappingTarget ItemSale itemSale);
    List<ItemVendaOut> toListItemVendaOut(List<ItemSale> itenVendas);

}

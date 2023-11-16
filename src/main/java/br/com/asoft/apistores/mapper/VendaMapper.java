package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.controller.VendaController;
import br.com.asoft.apistores.inp.VendaInp;
import br.com.asoft.apistores.model.Venda;
import br.com.asoft.apistores.out.VendaOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    VendaOut toVendaOut(Venda venda);

    Venda toVenda(VendaInp vendaInp);

    Venda copyToVendaInp(VendaInp vendaInp, @MappingTarget Venda venda);

    List<VendaOut> toListVendaOut(List<Venda> vendas);


}

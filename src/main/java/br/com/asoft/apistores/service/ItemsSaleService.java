package br.com.asoft.apistores.service;


import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.ItemsSale;
import br.com.asoft.apistores.model.Sales;
import br.com.asoft.apistores.respository.ItemsSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsSaleService {

    private final ItemsSaleRepository itemsSaleRepository;

    private final SalesService salesService;

    public Page<ItemsSale> allTodosPage(Pageable pageable){
        return itemsSaleRepository.findAll(pageable);
    }

    public List<ItemsSale> allTodos(){
       return itemsSaleRepository.findAll();
    }

    public ItemsSale findId(Long id){
        return tryOrFaill(id);
    }

    public ItemsSale saveItemVenda(ItemsSale itenVenda){

        Long vendaId = itenVenda.getSales().getId();

        Sales sales = salesService.tryOrFail(vendaId);

        itenVenda.setSales(sales);

        return itemsSaleRepository.save(itenVenda);
    }

    public void deletarItemVenda(Long id){
        ItemsSale itemsSale = findId(id);
        itemsSaleRepository.delete(itemsSale);
        itemsSaleRepository.flush();
    }

    public ItemsSale tryOrFaill(Long id){
        return itemsSaleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("ItenVenda",id));
    }

//    public ByteArrayInputStream relatorioItemVenda() throws IOException {
//
//        Reports reports = new Reports(Reports.Page.VERTICAL);
//
//        reports.addParagraph(new Paragraph("Lista dos Itens da Sales")
//                .setMargins(1f,5f,1f,5f)
//                .setFontSize(28)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));
//
//        reports.addNewLine();
//
//        reports.openTable(1f,1f,1f,1f,1f);
//
//        reports.addTableHeader("Codigo Item","Codigo Sales","Quantidade","Valor Unidade","Valor Total");
//
//        List<ItemSale> itemSales = allTodos();
//
//        for (ItemSale itemSale : itemSales) {
//
//            reports.addCellCenter(itemSale.getId());
//            reports.addCellCenter(itemSale.getSales().getId());
//            reports.addCellCenter(itemSale.getQuant());
//            reports.addCellCenter(itemSale.getValorUnid());
//            reports.addCellCenter(itemSale.getValorTotal());
//
//        }
//
//        reports.closeTable();
//
//        reports.closeDocument();
//
//        return reports.getByteArrayInputStream();
//    }

}

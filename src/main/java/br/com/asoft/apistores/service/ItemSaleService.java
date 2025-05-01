package br.com.asoft.apistores.service;


import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.ItemSale;
import br.com.asoft.apistores.model.Sales;
import br.com.asoft.apistores.respository.ItemSaleRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSaleService {

    private final ItemSaleRepository itemSaleRepository;

    private final SalesService salesService;

    public Page<ItemSale> allTodosPage(Pageable pageable){
        return itemSaleRepository.findAll(pageable);
    }

    public List<ItemSale> allTodos(){
       return itemSaleRepository.findAll();
    }

    public ItemSale findId(Long id){
        return tryOrFaill(id);
    }

    public ItemSale saveItemVenda(ItemSale itenVenda){

        Long vendaId = itenVenda.getSales().getId();

        Sales sales = salesService.tryOrFail(vendaId);

        itenVenda.setSales(sales);

        return itemSaleRepository.save(itenVenda);
    }

    public void deletarItemVenda(Long id){
        ItemSale itemSale = findId(id);
        itemSaleRepository.delete(itemSale);
        itemSaleRepository.flush();
    }

    public ItemSale tryOrFaill(Long id){
        return itemSaleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("ItenVenda",id));
    }

    public ByteArrayInputStream relatorioItemVenda() throws IOException {

        Reports reports = new Reports(Reports.Page.VERTICAL);

        reports.addParagraph(new Paragraph("Lista dos Itens da Sales")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f,1f,1f);

        reports.addTableHeader("Codigo Item","Codigo Sales","Quantidade","Valor Unidade","Valor Total");

        List<ItemSale> itemSales = allTodos();

        for (ItemSale itemSale : itemSales) {

            reports.addCellCenter(itemSale.getId());
            reports.addCellCenter(itemSale.getSales().getId());
            reports.addCellCenter(itemSale.getQuant());
            reports.addCellCenter(itemSale.getValorUnid());
            reports.addCellCenter(itemSale.getValorTotal());

        }

        reports.closeTable();

        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }

}

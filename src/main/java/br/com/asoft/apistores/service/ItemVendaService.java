package br.com.asoft.apistores.service;


import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.ItemVenda;
import br.com.asoft.apistores.model.Venda;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.ItemVendaRepository;
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
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;

    private final VendaService vendaService;

    public Page<ItemVenda> allTodosPage(Pageable pageable){
        return itemVendaRepository.findAll(pageable);
    }

    public List<ItemVenda> allTodos(){
       return itemVendaRepository.findAll();
    }

    public ItemVenda findId(Long id){
        return tryOrFaill(id);
    }

    public ItemVenda saveItemVenda(ItemVenda itenVenda){

        Long vendaId = itenVenda.getVenda().getId();

        Venda venda = vendaService.tryOrFail(vendaId);

        itenVenda.setVenda(venda);

        return itemVendaRepository.save(itenVenda);
    }

    public void deletarItemVenda(Long id){
        ItemVenda itemVenda = findId(id);
        itemVendaRepository.delete(itemVenda);
        itemVendaRepository.flush();
    }

    public ItemVenda tryOrFaill(Long id){
        return itemVendaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("ItenVenda",id));
    }

    public ByteArrayInputStream relatorioItemVenda() throws IOException {

        Reports reports = new Reports(Reports.Page.VERTICAL);

        reports.addParagraph(new Paragraph("Lista dos Itens da Venda")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f,1f,1f);

        reports.addTableHeader("Codigo Item","Codigo Venda","Quantidade","Valor Unidade","Valor Total");

        List<ItemVenda> itemVendas = allTodos();

        for (ItemVenda itemVenda : itemVendas) {

            reports.addCellCenter(itemVenda.getId());
            reports.addCellCenter(itemVenda.getVenda().getId());
            reports.addCellCenter(itemVenda.getQuant());
            reports.addCellCenter(itemVenda.getValorUnid());
            reports.addCellCenter(itemVenda.getValorTotal());

        }

        reports.closeTable();

        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }

}

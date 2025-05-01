package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Sales;
import br.com.asoft.apistores.respository.SalesRepository;
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
public class SalesService {

    private final SalesRepository salesRepository;

    public Page<Sales> allTodas(Pageable pageable){
        return salesRepository.findAll(pageable);
    }
    public List<Sales> allTodas(){
        return salesRepository.findAll();
    }

    public Sales findId(Long id){
        return tryOrFail(id);
    }

    public Sales saveVenda(Sales sales){
        return salesRepository.save(sales);
    }

    public void deleteVenda(Long id){
        Sales sales = tryOrFail(id);
        salesRepository.delete(sales);
    }

    public Sales tryOrFail(Long id){

        return salesRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Sales",id));
    }


    public ByteArrayInputStream relatorioTodasVendas() throws IOException {

        Reports reports = new Reports(Reports.Page.VERTICAL);

        reports.addParagraph(new Paragraph("Vendas")
                .setFontSize(28)
                .setTextAlignment(TextAlignment.LEFT)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
        );

        reports.addNewLine();
        reports.openTable(1f,1f,1f,1f);
        reports.addTableHeader("Codigo","Users","Client","Valor Total");

        List<Sales> sales = allTodas();

//        for (Sales sales : sales) {
//            reports.addCellCenter(sales.getId());
//            reports.addCellCenter(sales.getUsers().getLogin());
//            //reports.addCellCenter(sales.getClient().getPessoa().getNome());
//           // reports.addCellCenter(sales.getValorTotal());
//        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();

    }
}

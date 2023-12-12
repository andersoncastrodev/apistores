package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Venda;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.VendaRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;

    public List<Venda> allTodas(){
        return vendaRepository.findAll();
    }

    public Venda findId(Long id){
        return tryOrFail(id);
    }

    public Venda saveVenda(Venda venda){
        return vendaRepository.save(venda);
    }

    public void deleteVenda(Long id){
        Venda venda = tryOrFail(id);
        vendaRepository.delete(venda);
    }

    public Venda tryOrFail(Long id){

        return vendaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Venda",id));
    }


    public ByteArrayInputStream relatorioTodasVendas() throws IOException {

        Reports reports = new Reports(false);

        reports.addParagraph(new Paragraph("Vendas")
                .setFontSize(28)
                .setTextAlignment(TextAlignment.LEFT)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
        );

        reports.addNewLine();
        reports.openTable(1f,1f,1f,1f);
        reports.addTableHeader("Codigo","Usuario","Cliente","Valor Total");

        List<Venda> vendas = allTodas();

        for (Venda venda: vendas) {
            reports.addCellCenter(venda.getId());
            reports.addCellCenter(venda.getUsuario().getLogin());
            reports.addCellCenter(venda.getCliente().getPessoa().getNome());
            reports.addCellCenter(venda.getValorTotal());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();

    }
}

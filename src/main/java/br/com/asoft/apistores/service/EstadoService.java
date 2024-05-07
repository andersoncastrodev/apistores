package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.EstadoRepository;
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
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public Page<Estado> allEstadosPage(Pageable pageable){
        return estadoRepository.findAll(pageable);
    }
    public List<Estado> allEstados(){
        return estadoRepository.findAll();
    }

    public Estado findId(Long id){
        return tryOrFail(id);
    }

    public Estado saveEstado(Estado estado){
       return estadoRepository.save(estado);
    }

    public void deleteEstado(Long id){

        Estado estado = findId(id);

        estadoRepository.delete(estado);
        estadoRepository.flush();

    }
    public Estado tryOrFail(Long id){
        return estadoRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundExceptions("Estado",id));
    }

    public ByteArrayInputStream relatorioEstado() throws IOException {

        Reports reports = new Reports(Reports.Page.HORIZONTAL);

        reports.addParagraph(new Paragraph("Lista de Estados")
                .setMargins(1f,5f,1f,5)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();
        reports.openTable(1f,1f,1f);
        reports.addTableHeader("Codigo","Nome","Estado");

        List<Estado> estados = allEstados();

        for (Estado estado: estados) {

            reports.addCellCenter(estado.getId());
            reports.addCellCenter(estado.getNome());
            reports.addCellCenter(estado.getSigla());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }


}

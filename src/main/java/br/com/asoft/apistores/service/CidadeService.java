package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.CidadeFilter;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.CidadeRepository;
import br.com.asoft.apistores.specifications.CidadeSpecification;
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
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    private final EstadoService estadoService;

    public Page<Cidade> allCidadesPage(CidadeFilter cidadeFilter,Pageable pageable){
        return cidadeRepository.findAll(CidadeSpecification.filter(cidadeFilter), pageable);
    }

    public List<Cidade> allCidades(){
        return cidadeRepository.findAll();
    }

    public Cidade findId(Long id){
        return tryOrFail(id);
    }

    public Cidade saveCidade(Cidade cidade){

        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoService.tryOrFail(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void deletarCidade(Long id){

        Cidade cidade = tryOrFail(id);

        cidadeRepository.delete(cidade);

        cidadeRepository.flush();

    }
    public Cidade tryOrFail(Long id){
        return cidadeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Cidade",id));
    }

    public ByteArrayInputStream relatorioCidade() throws IOException {

        Reports reports = new Reports(false);

        reports.addParagraph(new Paragraph("Lista de Cidades")
                .setMargins(1f,2f,2f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();
        reports.openTable(1f,1f,1f);
        reports.addTableHeader("Codigo","Nome","Estado");

        List<Cidade> cidades = allCidades();

        for (Cidade cidade : cidades){

            reports.addCellCenter(cidade.getId());
            reports.addCellCenter(cidade.getNome());
            reports.addCellCenter(cidade.getEstado().getNome());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }

}

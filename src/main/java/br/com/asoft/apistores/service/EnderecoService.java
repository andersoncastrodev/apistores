package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.EnderecoRepository;
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
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final CidadeService cidadeService;

    public Page<Endereco> allEnderecoPage(Pageable pageable){
        return enderecoRepository.findAll(pageable);
    }

    public List<Endereco> allEndereco(){
        return enderecoRepository.findAll();
    }

    public Endereco findId(Long id){
        return tryOrFail(id);
    }

    public Endereco saveEndereco(Endereco endereco){

        Long cidadeId = endereco.getCidade().getId();

        Cidade cidade = cidadeService.tryOrFail(cidadeId);

        endereco.setCidade(cidade);

        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(Long id) {

        Endereco endereco = findId(id);

        enderecoRepository.delete(endereco);
        enderecoRepository.flush();
    }

    public Endereco tryOrFail(Long id){
        return enderecoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Endereco",id));
    }

    public ByteArrayInputStream relatorioEndereco() throws IOException {

        Reports reports = new Reports(false);

        reports.addParagraph(new Paragraph("Lista de Endereco")
                .setMargins(1f,5f,1f,5)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();
        reports.openTable(1f,1f,1f,1f);
        reports.addTableHeader("Codigo","Rua","Cep","Cidade");

        List<Endereco> enderecos = allEndereco();

        for(Endereco endereco : enderecos){

            reports.addCellCenter(endereco.getId());
            reports.addCellCenter(endereco.getRua());
            reports.addCellCenter(endereco.getCep());
            reports.addCellCenter(endereco.getCidade().getNome());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }

}

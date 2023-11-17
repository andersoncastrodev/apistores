package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.PessoaRepository;
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
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<Pessoa> allPessoas(){
        return pessoaRepository.findAll();
    }

    public Pessoa findId(Long id){
        return tryOrFail(id);
    }

    public Pessoa savePessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id){
        Pessoa pessoa = findId(id);
        pessoaRepository.delete(pessoa);
        pessoaRepository.flush();
    }

    public Pessoa tryOrFail(Long id){
        return pessoaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Pessoa",id));
    }

    public ByteArrayInputStream relatorioTodasPessoas() throws IOException {

        Reports reports = Reports.getInstance();

        reports.addParagraph(new Paragraph("Lista de Pessoas")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(3);

        reports.addTableHeader("Codigo","Nome","Telefone");

        List<Pessoa> pessoas = allPessoas();

        for (Pessoa pessoa : pessoas){

            reports.addTableColumn(pessoa.getId());
            reports.addTableColumn(pessoa.getNome());
            reports.addTableColumn(pessoa.getTelefone());

        }

        //reports.addTableFooter();

        reports.closeTable();

//        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }
}

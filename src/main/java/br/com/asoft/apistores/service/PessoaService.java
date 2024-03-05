package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dtointerface.PessoaNome;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.PessoaRepository;
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
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Page<Pessoa> allPessoasPage(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }
    public List<Pessoa> allPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa findId(Long id){
        return tryOrFail(id);
    }

    //Consulta Customizados para teste das Consultas
    // no Repository

    public List<PessoaNome> todosPessoaPorOrdemDescrente() {
        return pessoaRepository.findAllByOrderByIdDesc();
    }

    public List<Pessoa> todosPessoasPorOrdemAcrecente(String nome) {

        return pessoaRepository.findAllByNomeOrderByIdAsc(nome);
    }

    public boolean verificaSePessoaExiste(String nome) {
        return pessoaRepository.existsByNome(nome);
    }


    // ------   FIM DAS CONSULTA CUSTOMIZADAS ------------ //

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

        Reports reports = new Reports(false);

        reports.addParagraph(new Paragraph("Lista de Pessoas")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f);

        reports.addTableHeader("Codigo","Nome","Telefone");

        List<Pessoa> pessoas = allPessoas();

        for (Pessoa pessoa : pessoas){

            reports.addCellCenter(pessoa.getId());
            reports.addCellCenter(pessoa.getNome());
            reports.addCellCenter(pessoa.getTelefone());

        }

        reports.closeTable();

        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }
}

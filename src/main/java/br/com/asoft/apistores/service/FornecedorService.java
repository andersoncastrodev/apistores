package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.FornecedorRepository;
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
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public Page<Fornecedor> allTodosPage(Pageable pageable){
        return fornecedorRepository.findAll(pageable);
    }

    public List<Fornecedor> allTodos(){
        return fornecedorRepository.findAll();
    }

    public Fornecedor findId(Long id){
        return tryOrFail(id);
    }

    public Fornecedor saveFonecedor(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public void deletarFornecedor(Long id){
        Fornecedor fornecedor = findId(id);
        fornecedorRepository.delete(fornecedor);
        fornecedorRepository.flush();
    }
    public Fornecedor tryOrFail(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Fornecedor", id));
    }

    public ByteArrayInputStream relatorioFornecedor() throws IOException {

        Reports reports = new Reports(false);

        reports.addParagraph( new Paragraph("Lista de Fornecedores")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();
        reports.openTable(1f,1f,1f,1f);
        reports.addTableHeader("Codigo","Nome","Nome Fantasia","CNPJ / CPF");

        List<Fornecedor> fornecedors = allTodos();

        for (Fornecedor fornecedor : fornecedors) {
            reports.addCellCenter(fornecedor.getId());
            reports.addCellCenter(fornecedor.getNome());
            reports.addCellCenter(fornecedor.getNomeFantasia());
            reports.addCellCenter(fornecedor.getCpfcnpj());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }
}

package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Supplier;
import br.com.asoft.apistores.respository.SuppilerRepository;
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
public class SuppilerService {

    private final SuppilerRepository suppilerRepository;

    public Page<Supplier> allTodosPage(Pageable pageable){
        return suppilerRepository.findAll(pageable);
    }

    public List<Supplier> allTodos(){
        return suppilerRepository.findAll();
    }

    public Supplier findId(Long id){
        return tryOrFail(id);
    }

    public Supplier saveFonecedor(Supplier supplier){
        return suppilerRepository.save(supplier);
    }

    public void deletarFornecedor(Long id){
        Supplier supplier = findId(id);
        suppilerRepository.delete(supplier);
        suppilerRepository.flush();
    }
    public Supplier tryOrFail(Long id){
        return suppilerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Supplier", id));
    }

    public ByteArrayInputStream relatorioFornecedor() throws IOException {

        Reports reports = new Reports(Reports.Page.VERTICAL);

        reports.addParagraph( new Paragraph("Lista de Fornecedores")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();
        reports.openTable(1f,1f,1f,1f);
        reports.addTableHeader("Codigo","Nome","Nome Fantasia","CNPJ / CPF");

        List<Supplier> suppliers = allTodos();

        for (Supplier supplier : suppliers) {
            reports.addCellCenter(supplier.getId());
            reports.addCellCenter(supplier.getNome());
            reports.addCellCenter(supplier.getNomeFantasia());
            reports.addCellCenter(supplier.getCpfcnpj());
        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }
}

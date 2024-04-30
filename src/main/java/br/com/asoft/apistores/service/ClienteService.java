package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.ClienteFilter;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.ClienteRepository;
import br.com.asoft.apistores.specifications.ClienteSpecification;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final PessoaService pessoaService;

    public Page<Cliente> allClientePage(ClienteFilter clienteFilter, Pageable pageable){
        return clienteRepository.findAll(ClienteSpecification.filter(clienteFilter),pageable);
    }

    public List<Cliente> findAllCliente() {
        return clienteRepository.findAll();
    }

    public Cliente findId(Long id){
        return tryOrFail(id);
    }

    public Cliente saveCliente(Cliente cliente){

        Pessoa pessoa = pessoaService.findId(cliente.getPessoa().getId());

        cliente.setPessoa(pessoa);

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id){

        Cliente cliente = findId(id);

        clienteRepository.delete(cliente);

        clienteRepository.flush();
    }

    public Cliente tryOrFail(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Cliente",id));
    }

    public ByteArrayInputStream relatorioCliente() throws IOException {

        Reports reports = new Reports(false);

        reports.addParagraph( new Paragraph("Lista de Cliente")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();
        reports.openTable(1f,1f,1f,1f);
        reports.addTableHeader("Codigo Pessoa","Nome","Telefone","Tipo");

        List<Cliente> clientes = findAllCliente();

        for (Cliente cliente: clientes) {

            reports.addCellCenter(cliente.getPessoa().getId());
            reports.addCellCenter(cliente.getPessoa().getNome());
            reports.addCellCenter(cliente.getPessoa().getTelefone());
            reports.addCellCenter(cliente.getTipo());

        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();

    }
}

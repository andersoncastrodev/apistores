package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.ClienteFilter;
import br.com.asoft.apistores.model.Client;
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
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final PessoaService pessoaService;

    public Page<Client> allClientePage(ClienteFilter clienteFilter, Pageable pageable){

        return clienteRepository.findAll(ClienteSpecification.filter(clienteFilter),pageable);
    }

    public Page<Client> allClientePage2(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public List<Client> findAllCliente() {
        return clienteRepository.findAll();
    }

    public Client findId(Long id){
        return tryOrFail(id);
    }

    public Client saveCliente(Client client){

        Pessoa pessoa = pessoaService.findId(client.getPessoa().getId());

        client.setPessoa(pessoa);

        return clienteRepository.save(client);
    }

    public void deleteCliente(Long id){

        Client client = findId(id);

        clienteRepository.delete(client);

        clienteRepository.flush();
    }

    public Client tryOrFail(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Client",id));
    }

    public ByteArrayInputStream relatorioCliente() throws IOException {

        Reports reports = new Reports(Reports.Page.HORIZONTAL);

        reports.addParagraph( new Paragraph("Lista de Client")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f,1f);

        reports.addTableHeader("Codigo Pessoa","Nome","Telefone","Tipo");

        List<Client> clients = findAllCliente();

        for (Client client : clients) {

            reports.addCellCenter(client.getPessoa().getId());
            reports.addCellCenter(client.getPessoa().getNome());
            reports.addCellCenter(client.getPessoa().getTelefone());
            reports.addCellCenter(client.getTipo());

        }

        reports.closeTable();
        reports.closeDocument();

        return reports.getByteArrayInputStream();

    }
}

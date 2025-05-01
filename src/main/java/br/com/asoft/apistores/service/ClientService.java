package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.ClientFilter;
import br.com.asoft.apistores.model.Client;
import br.com.asoft.apistores.respository.ClientRepository;
import br.com.asoft.apistores.specifications.ClienteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Page<Client> allClientePage(ClientFilter clientFilter, Pageable pageable){

        return clientRepository.findAll(ClienteSpecification.filter(clientFilter),pageable);
    }

    public Page<Client> allClientePage2(Pageable pageable){
        return clientRepository.findAll(pageable);
    }

    public List<Client> findAllCliente() {
        return clientRepository.findAll();
    }

    public Client findId(Long id){
        return tryOrFail(id);
    }

    public Client saveCliente(Client client){
//
//        Pessoa pessoa = pessoaService.findId(client.getPessoa().getId());
//
//        client.setPessoa(pessoa);

        return clientRepository.save(client);
    }

    public void deleteCliente(Long id){

        Client client = findId(id);

        clientRepository.delete(client);

        clientRepository.flush();
    }

    public Client tryOrFail(Long id){
        return clientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Client",id));
    }

//    public ByteArrayInputStream relatorioCliente() throws IOException {
//
//        Reports reports = new Reports(Reports.Page.HORIZONTAL);
//
//        reports.addParagraph( new Paragraph("Lista de Client")
//                .setMargins(1f,5f,1f,5f)
//                .setFontSize(28)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));
//
//        reports.addNewLine();
//
//        reports.openTable(1f,1f,1f,1f);
//
//        reports.addTableHeader("Codigo Pessoa","Nome","Telefone","Tipo");
//
//        List<Client> clients = findAllCliente();
//
//        for (Client client : clients) {
//
//            reports.addCellCenter(client.getPessoa().getId());
//            reports.addCellCenter(client.getPessoa().getNome());
//            reports.addCellCenter(client.getPessoa().getTelefone());
//            reports.addCellCenter(client.getTipo());
//
//        }
//
//        reports.closeTable();
//        reports.closeDocument();
//
//        return reports.getByteArrayInputStream();
//
//    }
}

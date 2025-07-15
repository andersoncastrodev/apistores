package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.ClientFilter;
import br.com.asoft.apistores.model.Customer;
import br.com.asoft.apistores.respository.CustomerRepository;
import br.com.asoft.apistores.specifications.ClienteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<Customer> allClientePage(ClientFilter clientFilter, Pageable pageable){

        return customerRepository.findAll(ClienteSpecification.filter(clientFilter),pageable);
    }

    public Page<Customer> allClientePage2(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public List<Customer> findAllCliente() {
        return customerRepository.findAll();
    }

    public Customer findId(Long id){
        return tryOrFail(id);
    }

    public Customer saveCliente(Customer customer){
//
//        Pessoa pessoa = pessoaService.findId(client.getPessoa().getId());
//
//        client.setPessoa(pessoa);

        return customerRepository.save(customer);
    }

    public void deleteCliente(Long id){

        Customer customer = findId(id);

        customerRepository.delete(customer);

        customerRepository.flush();
    }

    public Customer tryOrFail(Long id){
        return customerRepository.findById(id)
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

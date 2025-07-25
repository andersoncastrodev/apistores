package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.respository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> allEndereco(){
        return addressRepository.findAll();
    }

    public Address findId(Long id){
        return tryOrFail(id);
    }

    @Transactional
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    public void deleteEndereco(Long id) {
        Address address = findId(id);
        addressRepository.delete(address);
        addressRepository.flush();
    }

    public Address tryOrFail(Long id){
        return addressRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Address",id));
    }

//    public ByteArrayInputStream relatorioEndereco() throws IOException {
//
//        Reports reports = new Reports(Reports.Page.VERTICAL);
//
//        reports.addParagraph(new Paragraph("Lista de Address")
//                .setMargins(1f,5f,1f,5)
//                .setFontSize(28)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));
//
//        reports.addNewLine();
//        reports.openTable(1f,1f,1f,1f);
//        reports.addTableHeader("Codigo","Rua","Cep","City");
//
//        List<Address> addresses = allEndereco();
//
//        for(Address address : addresses){
//
//            reports.addCellCenter(address.getId());
//            reports.addCellCenter(address.getRua());
//            reports.addCellCenter(address.getCep());
//            reports.addCellCenter(address.getCity().getNome());
//        }
//
//        reports.closeTable();
//        reports.closeDocument();
//
//        return reports.getByteArrayInputStream();
//    }

}

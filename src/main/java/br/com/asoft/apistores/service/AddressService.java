package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.AddressFilter;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.model.City;
import br.com.asoft.apistores.respository.AddressRepository;
import br.com.asoft.apistores.specifications.EnderecoSpecification;
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
public class AddressService {

    private final AddressRepository addressRepository;

    private final CityService cityService;

    public Page<Address> allEnderecoPage(AddressFilter addressFilter, Pageable pageable){
        return addressRepository.findAll(EnderecoSpecification.filter(addressFilter), pageable);
    }

    public List<Address> allEndereco(){
        return addressRepository.findAll();
    }

    public Address findId(Long id){
        return tryOrFail(id);
    }

    public Address saveEndereco(Address address){

        Long cidadeId = address.getCity().getId();

        City city = cityService.tryOrFail(cidadeId);

        address.setCity(city);

        return addressRepository.save(address);
    }

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

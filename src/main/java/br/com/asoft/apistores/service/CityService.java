package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.CityFilter;
import br.com.asoft.apistores.model.City;
import br.com.asoft.apistores.model.State;
import br.com.asoft.apistores.respository.CityRepository;
import br.com.asoft.apistores.specifications.CidadeSpecification;
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
public class CityService {

    private final CityRepository cityRepository;

    private final StateService stateService;

    public Page<City> allCidadesPage(CityFilter cityFilter, Pageable pageable){
        return cityRepository.findAll(CidadeSpecification.filter(cityFilter), pageable);
    }

    public List<City> allCidades(){
        return cityRepository.findAll();
    }

    public City findId(Long id){
        return tryOrFail(id);
    }

    public City saveCidade(City city){

        Long estadoId = city.getState().getId();

        State state = stateService.tryOrFail(estadoId);

        city.setState(state);

        return cityRepository.save(city);
    }

    public void deletarCidade(Long id){

        City city = tryOrFail(id);

        cityRepository.delete(city);

        cityRepository.flush();

    }
    public City tryOrFail(Long id){
        return cityRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("City",id));
    }

//    public ByteArrayInputStream relatorioCidade() throws IOException {
//
//        Reports reports = new Reports(Reports.Page.VERTICAL);
//
//        reports.addParagraph(new Paragraph("Lista de Cidades")
//                .setMargins(1f,2f,2f,5f)
//                .setFontSize(28)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));
//
//        reports.addNewLine();
//        reports.openTable(1f,1f,1f);
//        reports.addTableHeader("Codigo","Nome","State");
//
//        List<City> cities = allCidades();
//
//        for (City city : cities){
//
//            reports.addCellCenter(city.getId());
//            reports.addCellCenter(city.getNome());
//            reports.addCellCenter(city.getState().getNome());
//        }
//
//        reports.closeTable();
//        reports.closeDocument();
//
//        return reports.getByteArrayInputStream();
//    }

}

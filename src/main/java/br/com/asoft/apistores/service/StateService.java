package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.StateFilter;
import br.com.asoft.apistores.model.State;
import br.com.asoft.apistores.respository.StateRepository;
import br.com.asoft.apistores.specifications.EstadoSpecification;
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
public class StateService {

    private final StateRepository stateRepository;

    public Page<State> allEstadosPage(StateFilter stateFilter, Pageable pageable) {
        return stateRepository.findAll(EstadoSpecification.filter(stateFilter), pageable);
    }
    public List<State> allEstados() {
        return stateRepository.findAll();
    }

    public State findId(Long id) {
        return tryOrFail(id);
    }

    public State saveEstado(State state) {
       return stateRepository.save(state);
    }

    public void deleteEstado(Long id){

        State state = findId(id);

        stateRepository.delete(state);
        stateRepository.flush();

    }
    public State tryOrFail(Long id) {
        return stateRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundExceptions("State",id));
    }

//    public ByteArrayInputStream relatorioEstado() throws IOException {
//
//        Reports reports = new Reports(Reports.Page.HORIZONTAL);
//
//        reports.addParagraph(new Paragraph("Lista de Estados")
//                .setMargins(1f,5f,1f,5)
//                .setFontSize(28)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));
//
//        reports.addNewLine();
//        reports.openTable(1f,1f,1f);
//        reports.addTableHeader("Codigo","Nome","State");
//
//        List<State> states = allEstados();
//
//        for (State state : states) {
//
//            reports.addCellCenter(state.getId());
//            reports.addCellCenter(state.getNome());
//            reports.addCellCenter(state.getSigla());
//        }
//
//        reports.closeTable();
//        reports.closeDocument();
//
//        return reports.getByteArrayInputStream();
//    }


}

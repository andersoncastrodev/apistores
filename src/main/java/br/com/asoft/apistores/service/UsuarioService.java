package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Users;

import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.UsuarioRepository;
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
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Page<Users> allTodos(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public List<Users> allTodos(){
        return usuarioRepository.findAll();
    }

    public Users findId(Long id){
        return tryOrFail(id);
    }

    public Users saveUsuario(Users users){
        return usuarioRepository.save(users);
    }

    public void deleteUsuario(Long id) {

        Users users = findId(id);

        usuarioRepository.delete(users);
        usuarioRepository.flush();

    }

    public Users tryOrFail(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundExceptions("Users",id) );
    }

    public ByteArrayInputStream relatorioUsuarios() throws IOException {

        Reports reports = new Reports(Reports.Page.VERTICAL);

        reports.addParagraph(new Paragraph("Lista de Usuarios")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f,1f);

        reports.addTableHeader("Codigo","Login","Senha","Nome");


        List<Users> users = allTodos();

        for (Users users : users){
            reports.addCellCenter(users.getId());
            reports.addCellCenter(users.getLogin());
            reports.addCellCenter(users.getSenha());
            reports.addCellCenter(users.getPessoa().getNome());
        }

        reports.closeTable();

        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }

}

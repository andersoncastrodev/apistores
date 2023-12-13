package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Usuario;

import br.com.asoft.apistores.relatorio.Reports;
import br.com.asoft.apistores.respository.UsuarioRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> allTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario findId(Long id){
        return tryOrFail(id);
    }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id){

        Usuario usuario = findId(id);

        usuarioRepository.delete(usuario);
        usuarioRepository.flush();

    }

    public Usuario tryOrFail(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundExceptions("Usuario",id) );
    }

    public ByteArrayInputStream relatorioUsuarios() throws IOException {

        Reports reports = new Reports(false);

        reports.addParagraph(new Paragraph("Lista de Usuarios")
                .setMargins(1f,5f,1f,5f)
                .setFontSize(28)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

        reports.addNewLine();

        reports.openTable(1f,1f,1f,1f);

        reports.addTableHeader("Codigo","Login","Senha","Nome");


        List<Usuario> usuarios = allTodos();

        for (Usuario usuario: usuarios){
            reports.addCellCenter(usuario.getId());
            reports.addCellCenter(usuario.getLogin());
            reports.addCellCenter(usuario.getSenha());
            reports.addCellCenter(usuario.getPessoa().getNome());
        }

        reports.closeTable();

        reports.closeDocument();

        return reports.getByteArrayInputStream();
    }

}

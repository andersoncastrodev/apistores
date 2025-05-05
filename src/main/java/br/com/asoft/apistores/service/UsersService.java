package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.respository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder; //Cria uma maneira de encripar as senhas do usuario

    public Page<Users> findAllUsers(Pageable pageable){
        return usersRepository.findAll(pageable);
    }

    public Optional<Users> findByUsersName(String name) {
        return usersRepository.findByLogin(name);
    }

    public boolean isLoginCorrect(String login, String password) {

        Optional<Users> byUsersName = findByUsersName(login);

       // boolean matches = bCryptPasswordEncoder.matches(password, byUsersName.get().getPassword());

        boolean passwordValid = password.equals(byUsersName.get().getPassword());


        if (byUsersName.isEmpty() || passwordValid) {
            return true;
        }
        return false;
    }

//    public List<Users> allTodos(){
//        return usersRepository.findAll();
//    }

    public Users findId(Long id){
        return tryOrFail(id);
    }

    public Users saverUsers(Users users) {
        return usersRepository.save(users);
    }

    public void deleteUsuario(Long id) {

        Users users = findId(id);

        usersRepository.delete(users);
        usersRepository.flush();

    }

    public Users tryOrFail(Long id){
        return usersRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundExceptions("Users",id) );
    }

//    public ByteArrayInputStream relatorioUsuarios() throws IOException {
//
//        Reports reports = new Reports(Reports.Page.VERTICAL);
//
//        reports.addParagraph(new Paragraph("Lista de Usuarios")
//                .setMargins(1f,5f,1f,5f)
//                .setFontSize(28)
//                .setTextAlignment(TextAlignment.CENTER)
//                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));
//
//        reports.addNewLine();
//
//        reports.openTable(1f,1f,1f,1f);
//
//        reports.addTableHeader("Codigo","Login","Senha","Nome");
//
//
//        List<Users> users = allTodos();
//
//        for (Users users : users){
//            reports.addCellCenter(users.getId());
//            reports.addCellCenter(users.getLogin());
//            reports.addCellCenter(users.getSenha());
//            reports.addCellCenter(users.getPessoa().getNome());
//        }
//
//        reports.closeTable();
//
//        reports.closeDocument();
//
//        return reports.getByteArrayInputStream();
//    }

}

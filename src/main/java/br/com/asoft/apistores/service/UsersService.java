package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.BusinessException;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.respository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder; //Cria uma maneira de encripar as senhas do usuario

    public Page<Users> findAllUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public Users findById(Long id) {
        return tryOrFail(id);
    }

    public Users saverUsers(Users users) {

        //Verificar se a pessoas esta cadastrada
        usersRepository.findByCpf(users.getCpf()).ifPresent(
                u -> { throw new BusinessException("Cpf ja cadastrado");
            }
        );

        //faz a encriptação da senha do usuario
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

        return usersRepository.save(users);
    }

    public Users findByLogin(String login) {
        return usersRepository.findByLogin(login).orElseThrow(() -> new EntityNotFoundExceptions("Usuário não encontrado"));
    }

    public void deleteUsuario(Long id) {

        Users users = findById(id);

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

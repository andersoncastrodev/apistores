package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.respository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    private final EstadoService estadoService;

    public List<Cidade> allCidades(){
        return cidadeRepository.findAll();
    }

    public Cidade findId(Long id){
        return tryOrFail(id);
    }

    public Cidade saveCidade(Cidade cidade){

        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoService.tryOrFail(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void deletarCidade(Long id){

        Cidade cidade = tryOrFail(id);

        cidadeRepository.delete(cidade);

        cidadeRepository.flush();

    }
    public Cidade tryOrFail(Long id){
        return cidadeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Cidade",id));
    }

}

package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.respository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> allEstados(){
        return estadoRepository.findAll();
    }

    public Estado findId(Long id){
        return tryOrFail(id);
    }

    public Estado saveEstado(Estado estado){
       return estadoRepository.save(estado);
    }

    public Estado tryOrFail(Long id){
        return estadoRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundExceptions("Estado",id));
    }
}

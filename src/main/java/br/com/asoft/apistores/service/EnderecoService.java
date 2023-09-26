package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.respository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final CidadeService cidadeService;

    public List<Endereco> allEndereco(){
        return enderecoRepository.findAll();
    }

    public Endereco findId(Long id){
        return tryOrFaill(id);
    }

    public Endereco saveEndereco(Endereco endereco){

        Long cidadeId = endereco.getCidade().getId();

        Cidade cidade = cidadeService.tryOrFaill(cidadeId);

        endereco.setCidade(cidade);

        return enderecoRepository.save(endereco);
    }

    public Endereco tryOrFaill(Long id){
        return enderecoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Endereco",id));
    }
}

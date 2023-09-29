package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.PessoaInp;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.out.PessoaOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    PessoaOut toPessoaOut(Pessoa pessoa);

    Pessoa toPessoa(PessoaInp pessoaInp);

    List<PessoaOut> toListPessoaOut(List<Pessoa> pessoas);

}

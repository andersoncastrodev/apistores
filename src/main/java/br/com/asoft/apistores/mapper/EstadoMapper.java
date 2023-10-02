package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.EstadoInp;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.model.Estado;
import br.com.asoft.apistores.out.EstadoOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    EstadoOut toEstadoOut(Estado estado);

    Estado toEstado(EstadoInp estadoInp);

    Estado copyToEstado(EstadoInp estadoInp, @MappingTarget Estado estado);

    List<EstadoOut> toListEstadoOut(List<Estado> estados);
}

package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.EstadoInp;
import br.com.asoft.apistores.model.State;
import br.com.asoft.apistores.out.EstadoOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    EstadoOut toEstadoOut(State state);

    State toEstado(EstadoInp estadoInp);

    State copyToEstado(EstadoInp estadoInp, @MappingTarget State state);

    List<EstadoOut> toListEstadoOut(List<State> states);
}

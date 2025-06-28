package br.com.asoft.apistores.service;

import br.com.asoft.apistores.model.State;
import br.com.asoft.apistores.respository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public List<State> allStates() {
        return stateRepository.findAll();
    }

}

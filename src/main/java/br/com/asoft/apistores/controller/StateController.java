package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.model.State;
import br.com.asoft.apistores.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/states")
@AllArgsConstructor
public class StateController {

    private final StateService stateService;

    @GetMapping
    public List<State> allStates(){
        return stateService.allStates();
    }

}

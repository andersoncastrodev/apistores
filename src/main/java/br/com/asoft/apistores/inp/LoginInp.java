package br.com.asoft.apistores.inp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class LoginInp {
    private String username;
    private String password;
}

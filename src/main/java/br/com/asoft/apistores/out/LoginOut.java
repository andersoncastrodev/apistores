package br.com.asoft.apistores.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginOut {
    private String accessToken;
    private Long expiresIn;
}

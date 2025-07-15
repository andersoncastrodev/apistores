package br.com.asoft.apistores.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String accessToken;

    private Long expiresToken; //tempo de expiração do token

    private String refreshToken;

    private Long expiresRefToken; //tempo de expiração do refresh token

}

package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.config.security.TokenService;
import br.com.zup.emerson.mercadolivre.controller.dto.request.LoginRequest;
import br.com.zup.emerson.mercadolivre.controller.dto.request.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest loginRequest) {

        //Dados do login
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginRequest.toModel();

        try {
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            String token = tokenService.gerarToken(authenticate);

            System.out.println(token);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }



    }


}

package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.config.security.AutenticacaoViaTokenFilter;
import br.com.zup.emerson.mercadolivre.config.security.TokenService;
import br.com.zup.emerson.mercadolivre.controller.dto.request.LoginRequest;
import br.com.zup.emerson.mercadolivre.controller.dto.response.TokenResponse;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import br.com.zup.emerson.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {



    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest loginRequest) {

        //Dados do login
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.toModel();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

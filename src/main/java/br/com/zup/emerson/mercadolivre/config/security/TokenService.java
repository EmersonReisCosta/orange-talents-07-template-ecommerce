package br.com.zup.emerson.mercadolivre.config.security;

import br.com.zup.emerson.mercadolivre.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${mercadolivre.jwt.expiration}")
    private String expiration;

    @Value("${mercadolivre.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authenticate) {

        Usuario logado = (Usuario) authenticate.getPrincipal(); //Recupera usuario Logado
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("API Merscado Livre") //Quem é que está gerando esse token
                .setSubject(logado.getLogin()) //Quem é o usuario autenticado que esse token pertence
                .setIssuedAt(hoje) //Data de geração do token
                .setExpiration(dataExpiracao) //Tempo de expiração
                .signWith(SignatureAlgorithm.HS256, secret) //Diz qual é o algoritimo de criptografia e a senha da aplicação para gerar o hash do token
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

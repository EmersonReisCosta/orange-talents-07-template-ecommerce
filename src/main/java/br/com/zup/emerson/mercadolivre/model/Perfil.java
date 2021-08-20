package br.com.zup.emerson.mercadolivre.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePerfil;

    public Long getId() {
        return id;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    @Override
    public String getAuthority() {
        return nomePerfil;
    }
}

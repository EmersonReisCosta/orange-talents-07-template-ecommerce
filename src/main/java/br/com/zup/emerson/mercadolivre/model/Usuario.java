package br.com.zup.emerson.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @Column(unique = true)
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;
    @CreationTimestamp
    private LocalDateTime horaCadastro;

    //Sempre que o usuario for carregado ele ja carrega a lista de perfis dele.
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioLogado")
    private Collection<Opiniao> opinioes = new HashSet<>();
    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Long getId() {
        return id;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public Collection<Opiniao> getOpinioes() {
        return opinioes;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return login.equals(usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}

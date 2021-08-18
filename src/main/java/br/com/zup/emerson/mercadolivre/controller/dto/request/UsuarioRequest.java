package br.com.zup.emerson.mercadolivre.controller.dto.request;

import br.com.zup.emerson.mercadolivre.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Deprecated
    public UsuarioRequest() {
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(){
       return new Usuario(this.login, this.senha);
    }
}

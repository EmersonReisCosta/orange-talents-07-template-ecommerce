package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.controller.dto.request.UsuarioRequest;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public String cadastraUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){

        return "Cadastrando Usuario...";
    }
}

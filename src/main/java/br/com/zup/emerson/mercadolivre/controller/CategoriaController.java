package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.controller.dto.request.CategoriaRequest;
import br.com.zup.emerson.mercadolivre.model.Categoria;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PostMapping
    public Categoria cadastraCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toModel();
        return categoria;
    }
}

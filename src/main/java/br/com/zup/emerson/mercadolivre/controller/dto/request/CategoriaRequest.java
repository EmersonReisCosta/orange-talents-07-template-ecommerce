package br.com.zup.emerson.mercadolivre.controller.dto.request;


import br.com.zup.emerson.mercadolivre.model.Categoria;
import br.com.zup.emerson.mercadolivre.repository.CategoriaRepository;
import br.com.zup.emerson.mercadolivre.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fildName = "nomeCategoria")
    private String nomeCategoria;
    private Long categoriaId;

    @Deprecated
    public CategoriaRequest() {
    }


    public CategoriaRequest(String nomeCategoria, Long categoria_id) {
        this.nomeCategoria = nomeCategoria;
        this.categoriaId = categoriaId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    // Categoria request deve receber o nome e a chave estrangeira de categoria.
    public Categoria toModel(CategoriaRepository categoriaRepository){
        if (categoriaId != null){
            Categoria categoriaEncontrada = categoriaRepository.findById(categoriaId).get();
            return new Categoria(nomeCategoria, categoriaEncontrada);
        }

        return new Categoria(nomeCategoria, null);

    }
}

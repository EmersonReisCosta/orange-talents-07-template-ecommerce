package br.com.zup.emerson.mercadolivre.controller.dto.request;

import br.com.zup.emerson.mercadolivre.model.Categoria;

public class CategoriaRequest {

    private String nomeCategoria;
    private Categoria categoria;

    public CategoriaRequest(String nomeCategoria, Categoria categoria) {
        this.nomeCategoria = nomeCategoria;
        this.categoria = categoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    // Categoria request deve receber o nome e a chave estrangeira de categoria.
    public Categoria toModel(){
        return new Categoria(this.nomeCategoria, this.categoria);
    }
}

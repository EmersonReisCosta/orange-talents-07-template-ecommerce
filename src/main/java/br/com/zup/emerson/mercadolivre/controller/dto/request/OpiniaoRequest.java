package br.com.zup.emerson.mercadolivre.controller.dto.request;

import br.com.zup.emerson.mercadolivre.model.Produto;
import br.com.zup.emerson.mercadolivre.model.Opiniao;
import br.com.zup.emerson.mercadolivre.repository.ProdutoRepository;

import javax.validation.constraints.*;
import java.util.Optional;

public class OpiniaoRequest {


    @NotNull
    @Max(5)
    @Min(1)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @NotNull
    private Long produtoId;

    public OpiniaoRequest(int nota, String titulo, String descricao, Long produtoId) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoId = produtoId;
    }

    @Deprecated
    public OpiniaoRequest() {
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Opiniao toModel(ProdutoRepository produtoRepository) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);

        return new Opiniao(nota, titulo, descricao, produto.get());
    }
}

package br.com.zup.emerson.mercadolivre.repository;

import br.com.zup.emerson.mercadolivre.model.Produto;
import br.com.zup.emerson.mercadolivre.model.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @NotNull
    private Usuario usuarioLogado;
    @NotNull
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(int nota, String titulo, String descricao, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
    }


}

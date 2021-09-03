package br.com.zup.emerson.mercadolivre.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank
    private String titulo;
    @CreationTimestamp
    private LocalDateTime instanteCriacao;
    @NotNull
    @ManyToOne
    private Usuario usuarioLogado;
    @NotNull
    @ManyToOne
    private Produto produto;

    public Pergunta(String titulo) {
        this.titulo = titulo;
    }
    @Deprecated
    public Pergunta() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(Id, pergunta.Id) && Objects.equals(titulo, pergunta.titulo) && Objects.equals(instanteCriacao, pergunta.instanteCriacao) && Objects.equals(usuarioLogado, pergunta.usuarioLogado) && Objects.equals(produto, pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, usuarioLogado, produto);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

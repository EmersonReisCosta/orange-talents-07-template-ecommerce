package br.com.zup.emerson.mercadolivre.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nomeCategoria;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @OneToMany(mappedBy = "categoria")
    private Categoria categoria;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nomeCategoria, Categoria categoria) {
        this.nomeCategoria = nomeCategoria;
        this.categoria = categoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Long getId() {
        return id;
    }
}

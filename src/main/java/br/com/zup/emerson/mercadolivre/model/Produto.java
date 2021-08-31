package br.com.zup.emerson.mercadolivre.model;

import br.com.zup.emerson.mercadolivre.controller.dto.request.CaracteristicaRequest;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidadeDisponivel;
    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;

    @CreationTimestamp
    private LocalDateTime instanteCadastro;

    @ManyToOne
    private Usuario usuarioLogado;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, int quantidadeDisponivel, String descricao, Categoria categoria, Usuario usuarioLogado, Set<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuarioLogado = usuarioLogado;

        caracteristicas.forEach(cr -> this.caracteristicas.add(cr.toModel(this)));

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}

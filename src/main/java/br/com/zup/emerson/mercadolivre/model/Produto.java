package br.com.zup.emerson.mercadolivre.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
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

    @NotNull
    @ManyToOne
    private Usuario usuarioLogado;


    public Produto(String nome, BigDecimal valor, int quantidadeDisponivel, Set<CaracteristicaProduto> caracteristicas, String descricao, Categoria categoria, LocalDateTime instanteCadastro, Usuario usuarioLogado) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instanteCadastro = instanteCadastro;
        this.usuarioLogado = usuarioLogado;
    }
}

package br.com.zup.emerson.mercadolivre.controller.dto.request;

import br.com.zup.emerson.mercadolivre.model.Categoria;
import br.com.zup.emerson.mercadolivre.model.Produto;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import br.com.zup.emerson.mercadolivre.repository.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidadeDisponivel;
    @JsonProperty(value = "caracteristicas")
    @Size(min = 3, message = "Cadastre no mínimo 3 características")
    @Valid
    private  Set<CaracteristicaRequest> caracteristicas;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    private Long categoriaId;

    @CreationTimestamp
    private LocalDateTime instanteCadastro;

    private Usuario usuarioLogado;

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidadeDisponivel, String descricao, Long categoriaId,  Set<CaracteristicaRequest> listaCaracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    @Deprecated
    public ProdutoRequest() {
    }

    public Optional<Produto> toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoriaObj = categoriaRepository.findById(categoriaId);
        Categoria categoria = categoriaObj.get();

        Produto produto = new Produto(nome, valor, quantidadeDisponivel, descricao,
                categoria, usuario, caracteristicas);

        return Optional.of(produto);
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

    public Set<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }
    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean caracteristicasIguais() {
        List<String> caracteristicasNomesCompara = new ArrayList<>();
        for (CaracteristicaRequest caracteristica : caracteristicas) {
            if (caracteristicasNomesCompara.contains(caracteristica.getNome())) {
                return true;
            }
            caracteristicasNomesCompara.add(caracteristica.getNome());
        }
        return false;
    }
}

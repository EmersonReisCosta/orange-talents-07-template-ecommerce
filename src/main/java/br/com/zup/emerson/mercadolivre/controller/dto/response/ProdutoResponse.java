package br.com.zup.emerson.mercadolivre.controller.dto.response;

import br.com.zup.emerson.mercadolivre.model.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ProdutoResponse {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Set<CaracteristicaProdutoResponse> caracteristicas;
    private Set<String> imagens;
    private SortedSet<String> perguntas;
    private Set<Map<String,String>> opinioes;
    private double mediaNotas;
    private final int totalNotas;

    public ProdutoResponse(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        // this.caracteristicas = produto.getCaracteristicas().stream().map(c -> new DetalheCaracteristicaProduto(c)).collect(Collectors.toSet());
        this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristicaProduto::new);
        this.imagens = produto.mapImagens(imagens -> imagens.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());

        Opinioes opinioes = produto.getOpinioes();

        this.opinioes = opinioes.mapOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(),"descricao", opiniao.getDescricao());
        });
}

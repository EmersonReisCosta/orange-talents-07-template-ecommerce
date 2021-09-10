package br.com.zup.emerson.mercadolivre.controller.dto.response;

import br.com.zup.emerson.mercadolivre.model.CaracteristicaProduto;

public class CaracteristicaProdutoResponse {
    private String nome;
    private String descricao;

    public CaracteristicaProdutoResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();

    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

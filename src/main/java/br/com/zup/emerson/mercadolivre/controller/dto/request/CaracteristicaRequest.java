package br.com.zup.emerson.mercadolivre.controller.dto.request;

import br.com.zup.emerson.mercadolivre.model.CaracteristicaProduto;
import br.com.zup.emerson.mercadolivre.model.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    @Deprecated
    public CaracteristicaRequest() {
    }

    public CaracteristicaProduto toModel(@NotNull Produto produto){
        return new CaracteristicaProduto(nome,descricao,produto);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}

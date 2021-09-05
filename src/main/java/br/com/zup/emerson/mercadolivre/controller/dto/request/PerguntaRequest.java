package br.com.zup.emerson.mercadolivre.controller.dto.request;

import br.com.zup.emerson.mercadolivre.model.Pergunta;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public PerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }
    @Deprecated
    public PerguntaRequest() {
    }

    public Pergunta toModel(){
        return new Pergunta(titulo);
    }
}

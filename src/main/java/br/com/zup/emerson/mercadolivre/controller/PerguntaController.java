package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.controller.dto.request.PerguntaRequest;
import br.com.zup.emerson.mercadolivre.model.Pergunta;
import br.com.zup.emerson.mercadolivre.model.Produto;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import br.com.zup.emerson.mercadolivre.repository.ProdutoRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    private ProdutoRepository produtoRepository;

    public PerguntaController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/{id}")
    public String cadastraPergunta(@PathVariable("id") Long id
            , @RequestBody PerguntaRequest perguntaRequest
            , @AuthenticationPrincipal Usuario usuario) {
        Pergunta pergunta = perguntaRequest.toModel();
        Produto produto = produtoRepository.findById(id).get();
        pergunta.setProduto(produto);
        pergunta.setUsuarioLogado(usuario);

        return "Cadastrando Pergunta";
    }
}

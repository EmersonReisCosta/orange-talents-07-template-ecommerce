package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.controller.dto.request.PerguntaRequest;
import br.com.zup.emerson.mercadolivre.model.Pergunta;
import br.com.zup.emerson.mercadolivre.model.Produto;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import br.com.zup.emerson.mercadolivre.repository.PerguntaRepository;
import br.com.zup.emerson.mercadolivre.repository.ProdutoRepository;
import br.com.zup.emerson.mercadolivre.utils.EnviaEmailFake;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;

    private EnviaEmailFake enviaEmail;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, EnviaEmailFake enviaEmail) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.enviaEmail = enviaEmail;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Pergunta> cadastraPergunta(@PathVariable("id") Long id
            , @RequestBody PerguntaRequest perguntaRequest
            , @AuthenticationPrincipal Usuario usuario) {
        Pergunta pergunta = perguntaRequest.toModel();
        Produto produto = produtoRepository.findById(id).get();
        pergunta.setProduto(produto);
        pergunta.setUsuarioLogado(usuario);
        perguntaRepository.save(pergunta);

        enviaEmail.send("<html>...</html>",
                "Nova pergunta", usuario.getLogin(),
                "noreply@mercadolivre.com.br",
                pergunta.getUsuarioLogado().getLogin());

        return ResponseEntity.ok(pergunta);
    }
}

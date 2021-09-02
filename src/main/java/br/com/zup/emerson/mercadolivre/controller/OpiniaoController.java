package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.controller.dto.request.OpiniaoRequest;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import br.com.zup.emerson.mercadolivre.model.Opiniao;
import br.com.zup.emerson.mercadolivre.repository.OpiniaoRepository;
import br.com.zup.emerson.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    ProdutoRepository produtoRepository;
    OpiniaoRepository opiniaoRepository;

    public OpiniaoController(ProdutoRepository produtoRepository, OpiniaoRepository opiniaoRepository) {
        this.produtoRepository = produtoRepository;
        this.opiniaoRepository = opiniaoRepository;
    }
    @PostMapping
    public ResponseEntity<?> cadastraOpiniao(@RequestBody @Valid OpiniaoRequest opiniaoRequest,
                                             @AuthenticationPrincipal Usuario usuarioLogado){

        Opiniao opiniao = opiniaoRequest.toModel(produtoRepository);
        opiniao.setUsuarioLogado(usuarioLogado);
        opiniaoRepository.save(opiniao);
        return ResponseEntity.ok().build();
    }
}

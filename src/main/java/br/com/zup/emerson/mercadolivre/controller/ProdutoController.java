package br.com.zup.emerson.mercadolivre.controller;


import br.com.zup.emerson.mercadolivre.controller.dto.request.ProdutoRequest;
import br.com.zup.emerson.mercadolivre.model.Produto;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import br.com.zup.emerson.mercadolivre.repository.CategoriaRepository;
import br.com.zup.emerson.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    ProdutoRepository produtoRepository;

    CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest,
                                                   @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> produto = produtoRequest.toModel(categoriaRepository, usuarioLogado);
        if (produto.isPresent()) {
            produtoRepository.save(produto.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

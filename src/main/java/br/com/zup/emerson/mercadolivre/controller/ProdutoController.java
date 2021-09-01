package br.com.zup.emerson.mercadolivre.controller;


import br.com.zup.emerson.mercadolivre.controller.dto.request.CaracteristicaRequest;
import br.com.zup.emerson.mercadolivre.controller.dto.request.ImagensRequest;
import br.com.zup.emerson.mercadolivre.controller.dto.request.ProdutoRequest;
import br.com.zup.emerson.mercadolivre.model.ImagensProduto;
import br.com.zup.emerson.mercadolivre.model.Produto;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import br.com.zup.emerson.mercadolivre.repository.CategoriaRepository;
import br.com.zup.emerson.mercadolivre.repository.ImagensRepository;
import br.com.zup.emerson.mercadolivre.repository.ProdutoRepository;
import br.com.zup.emerson.mercadolivre.repository.UsuarioRepository;
import br.com.zup.emerson.mercadolivre.utils.Uploader;
import br.com.zup.emerson.mercadolivre.utils.UploaderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    ProdutoRepository produtoRepository;

    CategoriaRepository categoriaRepository;

    UsuarioRepository usuarioRepository;

    ImagensRepository imagensRepository;

    @Autowired
    Uploader uploader;

    public ProdutoController(ProdutoRepository produtoRepository,
                             CategoriaRepository categoriaRepository,
                             UsuarioRepository usuarioRepository,
                             ImagensRepository imagensRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.imagensRepository = imagensRepository;
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

    @PostMapping("/{id}/imagens")
    public ResponseEntity<Produto> adicionaImagem(@PathVariable("id") Long id
            ,@Valid ImagensRequest imagensRequest
            , @AuthenticationPrincipal Usuario usuarioLogado){
        Optional<Usuario> dono = usuarioRepository.findById(id);
        Produto produto = produtoRepository.findById(id).get();
        if (!dono.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        if (!produto.pertenceAoUsuario(usuarioLogado, dono.get())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploader.enviar(imagensRequest.getImagens());

        produto.associaImagens(links);
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }
}

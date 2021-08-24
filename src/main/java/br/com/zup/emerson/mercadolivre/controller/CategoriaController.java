package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.controller.dto.request.CategoriaRequest;
import br.com.zup.emerson.mercadolivre.model.Categoria;
import br.com.zup.emerson.mercadolivre.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public Categoria cadastraCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return categoria;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> ListarCategoria(){
        List<Categoria> categorias = categoriaRepository.findAll();

        return ResponseEntity.ok().body(categorias);
    }
}

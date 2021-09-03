package br.com.zup.emerson.mercadolivre.controller;

import br.com.zup.emerson.mercadolivre.controller.dto.PerguntaRequest;
import br.com.zup.emerson.mercadolivre.model.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @PostMapping("/{id}")
    public String cadastraPergunta(@PathVariable("id") Long id
                                   ,@RequestBody PerguntaRequest perguntaRequest
                                   ,@AuthenticationPrincipal Usuario usuario){


        return "";
    }
}

package br.com.zup.emerson.mercadolivre.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderFake implements Uploader{

    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem -> "http://bucket.io/"+imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}

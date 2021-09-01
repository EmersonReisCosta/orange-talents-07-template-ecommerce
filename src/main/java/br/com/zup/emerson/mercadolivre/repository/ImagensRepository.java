package br.com.zup.emerson.mercadolivre.repository;

import br.com.zup.emerson.mercadolivre.model.ImagensProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagensRepository extends JpaRepository<ImagensProduto, Long> {

}

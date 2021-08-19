package br.com.zup.emerson.mercadolivre.repository;

import br.com.zup.emerson.mercadolivre.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

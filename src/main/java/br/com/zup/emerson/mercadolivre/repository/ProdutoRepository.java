package br.com.zup.emerson.mercadolivre.repository;

import br.com.zup.emerson.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

package br.com.zup.emerson.mercadolivre.repository;

import br.com.zup.emerson.mercadolivre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

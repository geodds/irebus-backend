package br.com.ifba.irebus.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifba.irebus.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


   // public List<Usuario> findByName(String nome);

   // public List<Usuario> findByEmail(String email);

    Optional<Usuario> findByLogin(String login);
}

package br.com.ifba.irebus.usuario.repository;

import br.com.ifba.irebus.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public List<Usuario> findByName();
    public List<Usuario> findByEmail();
}

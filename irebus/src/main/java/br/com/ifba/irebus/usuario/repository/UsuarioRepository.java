package br.com.ifba.irebus.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifba.irebus.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.name = :name")
    public List<Usuario> findByName();
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public List<Usuario> findByEmail();
}

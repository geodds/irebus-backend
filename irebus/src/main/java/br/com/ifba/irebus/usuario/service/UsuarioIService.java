package br.com.ifba.irebus.usuario.service;

import br.com.ifba.irebus.usuario.entity.Usuario;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface UsuarioIService {

    public abstract Page<Usuario> findAll(Pageable pageable);
    public abstract Usuario findById(Long id);
    public abstract Usuario save(Usuario usuario);
    public abstract void delete(Usuario usuario);
    public abstract Usuario update(Long id, Usuario usuario);

}

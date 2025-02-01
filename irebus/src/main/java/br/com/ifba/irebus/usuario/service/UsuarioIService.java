package br.com.ifba.irebus.usuario.service;

import br.com.ifba.irebus.usuario.entity.Usuario;

import java.util.List;

public interface UsuarioIService {

    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
    Usuario update(Long id, Usuario usuario);

}

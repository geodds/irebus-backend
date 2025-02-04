package br.com.ifba.irebus.usuario.service;


import br.com.ifba.irebus.exception.BusinessException;
import br.com.ifba.irebus.usuario.entity.Usuario;
import br.com.ifba.irebus.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

   /* public UsuarioService getUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        return null;
    }*/

    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public List<Usuario> findByName() {
        return usuarioRepository.findByName();
    }

    public List<Usuario> findByEmail() {
        return usuarioRepository.findByEmail();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()->new BusinessException("Recurso não encontrado!"));
    }
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public void updateUser(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Map<String, String> deleteUser(Long id) {
        usuarioRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario deletado com sucesso!");
        return response;
    }
}

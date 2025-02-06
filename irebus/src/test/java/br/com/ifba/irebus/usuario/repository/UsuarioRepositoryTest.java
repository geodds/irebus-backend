package br.com.ifba.irebus.usuario.repository;

import br.com.ifba.irebus.usuario.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;

@DataJpaTest //indica ao spring que eh uma classe de teste
@DisplayName("Teste no repository do usuario") //fornece um nome legivel para um teste
@ActiveProfiles //define que o perfil test deve estar ativo durante a execucao
public class UsuarioRepositoryTest  {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Find user by login when successful")
    public void findByLogin_whenSuccessful() {
        this.saveUsuario(this.createUsuario());

        Optional<Usuario> usuarioFound = usuarioRepository.findByLogin("geodds");

        //afirmacoes que validam se o programa ta nos conformes
        Assertions.assertThat(usuarioFound.isPresent()).isTrue(); //verifica se o usuario foi encontrado no repositorio
        Assertions.assertThat(usuarioFound.get()).isNotNull(); //garante que o objeto nao eh nulo
        Assertions.assertThat(usuarioFound.get().getLogin()).isEqualTo("geodds"); //verifica se os valores sao iguais, se nao for da falha no teste
    }

    private void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    private Usuario createUsuario() {
        return Usuario.builder()
                .name("geovana")
                .login("geodds")
                .email("geodourado@gmail.com")
                .password("12345678")
                .build();
    }

}

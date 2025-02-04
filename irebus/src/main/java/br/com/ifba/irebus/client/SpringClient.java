package br.com.ifba.irebus.client;

import br.com.ifba.irebus.usuario.dto.UsuarioPostRequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;


@Log4j2
public class SpringClient {

    public static void main(String[] args) {
        //cria um webclient para se conectar ao endpoint usuarios
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/usuarios/")//url base da api
                .defaultHeader(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE).build(); //cabecalho

        UsuarioPostRequestDto usuarioPostRequestDto = new UsuarioPostRequestDto();
        usuarioPostRequestDto.setNome("Geovana");
        usuarioPostRequestDto.setEmail("geovana@gmail.com");
        usuarioPostRequestDto.setLogin("geodds");
        usuarioPostRequestDto.setSenha("12345678");

        //realiza uma requisicao get para o endpoint findall
        String response = webClient.get()
                .uri("/findAll") //especifica a uri para a requisicao
                .retrieve() //envia a requisicao e inicia o processo de recuperacao da resposta
                .bodyToMono(String.class)//converte o copro da resposta para string
                //mono eh um tipo reativoque representa um unico valor assincrono
                .block(); //bloqueia ate receber a resposta

        log.info(response);

        String response2 = webClient.post()
                .uri("/save")
                .body(Mono.just(usuarioPostRequestDto), UsuarioPostRequestDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(response2);
    }
}

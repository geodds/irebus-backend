package br.com.ifba.irebus.usuario.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O nome eh obrigatorio!") //vaida se o campo nao eh nulo
    @NotBlank(message = "O nome nao pode ser vazio") //valida se o campo nao esta vazio
    private String nome;

    @JsonProperty(value = "email")
    @Email(message = "Email invalido!") //valida o email
    private String email;

    @JsonProperty(value = "login")
    @NotNull(message = "O login eh obrigatorio!")
    @NotBlank(message = "O login nao pode ser vazio")
    //define um tamanho para o campo
    @Size(min = 3, max = 15, message = "O login deve ter no minimo 3 e no maximo 15 caracteres.")
    private String login;

    @JsonProperty(value = "senha")
    @NotNull(message = "Insira uma senha")
    @NotBlank(message = "A senha nao pode ser vazia")
    private String senha;
}

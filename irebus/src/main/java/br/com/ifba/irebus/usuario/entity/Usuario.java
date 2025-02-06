package br.com.ifba.irebus.usuario.entity;

import br.com.ifba.irebus.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder //gera padrao de construcao de objetos
public class Usuario extends PersistenceEntity implements Serializable {

    @Column(name = "nome",nullable = false)
    private String name;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "login",nullable = false, unique = true)
    private String login;

    @Column(name = "senha",nullable = false)
    private String password;
}

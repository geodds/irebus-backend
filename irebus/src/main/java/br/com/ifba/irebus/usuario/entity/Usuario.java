package br.com.ifba.irebus.usuario.entity;

import br.com.ifba.irebus.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String name;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "login",nullable = false, unique = true)
    private String login;

    @Column(name = "senha",nullable = false)
    private String password;
}

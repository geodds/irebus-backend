package br.com.ifba.irebus.usuario.controller;

import br.com.ifba.irebus.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.irebus.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.irebus.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.irebus.usuario.entity.Usuario;
import br.com.ifba.irebus.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


@RestController
@RequestMapping(path = "/usuarios") //mapeia as requisicoes de usuarios
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ObjectMapperUtil objectMapperUtil;

   /* public UsuarioController(UsuarioService usuarioService, ObjectMapperUtil objectMapperUtil) {
        this.usuarioService = usuarioService;
        this.objectMapperUtil = objectMapperUtil;
    }*/

    //endpoint para retornar uma lista de usuaurios
    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    //retornar uma resposta http e a lista de usuarios mapeada para dto
    public ResponseEntity<Page<UsuarioGetResponseDto>>findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.usuarioService.findAll(pageable) //chama o metodo findall
                .map(c -> objectMapperUtil.map(c, UsuarioGetResponseDto.class))); //mapeia para o dto
    }                //funcao lambda, transforma obejto usuario do page em UsuarioGetResponse dto

    //endpoint para buscar usuarios pelo nome
    @GetMapping(path = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>findByName(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(
                        this.usuarioService.findByName(),
                        UsuarioGetResponseDto.class));
    }

    //endpoint para buscar usuarios pelo email
    @GetMapping(path = "/findByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>findByEmail(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(
                        this.usuarioService.findByEmail(),
                        UsuarioGetResponseDto.class));
    }

    //endpoint para buscar usuarios pelo id
    @PostMapping("/usuarios{id}")
    public ResponseEntity<UsuarioGetResponseDto> findById(@PathVariable("id") Long id){
        Usuario usuario = usuarioService.findById(id);
        UsuarioGetResponseDto responseDto = objectMapperUtil.map(usuario, UsuarioGetResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    //endpoint para salvar usuarios
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioPostRequestDto userPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(usuarioService.save(
                                (objectMapperUtil.map(userPostRequestDto,Usuario.class))),
                        UsuarioGetResponseDto.class));
    }

    //endpoint para atualizar um usuario
    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UsuarioPostRequestDto userPostRequestDto) {
        Usuario usuario = objectMapperUtil.map(userPostRequestDto, Usuario.class);
        usuarioService.updateUser(usuario);
        return ResponseEntity.noContent().build();
    }

    //endpoint para deletar um usuario
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.deleteUser(id));
    }
}

package br.com.fiap.flowmind.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    public record UsuarioCadastroRequest(String nome, String email, String senha) {}
    public record UsuarioLoginRequest(String email, String senha) {}

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioCadastroRequest request) {
        Usuario usuario = usuarioService.cadastrar(request.nome(), request.email(), request.senha());
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginRequest login) {
        Usuario usuario = usuarioService.login(login.email(), login.senha());
        return ResponseEntity.ok(usuario);
    }
}

package br.com.fiap.flowmind.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    // ---------- CADASTRAR USUÁRIO ----------
    public Usuario cadastrar(String nome, String email, String senha) {

        // opcional: impedir cadastro duplicado
        Optional<Usuario> existente = repository.findByEmail(email);

        if (existente.isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        return repository.save(usuario);
    }

    // ---------- BUSCAR POR ID ----------
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    // ---------- LOGIN ----------
    public Usuario login(String email, String senha) {
        return repository
                .findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new EntityNotFoundException("Credenciais inválidas"));
    }
}

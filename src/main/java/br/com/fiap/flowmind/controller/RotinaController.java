package br.com.fiap.flowmind.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.flowmind.model.RotinaDiaria;
import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.service.RotinaService;
import br.com.fiap.flowmind.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rotina")
@RequiredArgsConstructor
public class RotinaController {

    private final RotinaService rotinaService;
    private final UsuarioService usuarioService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> buscarRotinaDoDia(@PathVariable Long idUsuario) {

        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        try {
            RotinaDiaria rotina = rotinaService.buscarRotinaDoDia(usuario);
            return ResponseEntity.ok(rotina);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Nenhuma rotina encontrada para hoje.");
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> atualizarRotina(
        @PathVariable Long idUsuario,
        @RequestBody String novaRotina) {

    Usuario usuario = usuarioService.buscarPorId(idUsuario);

    RotinaDiaria atualizada = rotinaService.atualizarRotinaDoDia(usuario, novaRotina);

    return ResponseEntity.ok(atualizada);
    }
}

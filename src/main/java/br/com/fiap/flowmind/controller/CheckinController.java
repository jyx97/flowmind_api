package br.com.fiap.flowmind.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.flowmind.model.CheckinDiario;
import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.service.CheckinService;
import br.com.fiap.flowmind.service.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/checkins")
@AllArgsConstructor
public class CheckinController {

    private CheckinService checkinService;
    private UsuarioService usuarioService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<CheckinDiario> criar(
            @PathVariable Long usuarioId,
            @RequestBody CheckinDiario checkin) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        checkin.setUsuario(usuario);

        CheckinDiario criado = checkinService.salvar(checkin);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckinDiario> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(checkinService.buscar(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CheckinDiario>> listarPorUsuario(@PathVariable Long usuarioId) {
        Usuario u = usuarioService.buscarPorId(usuarioId);
        return ResponseEntity.ok(checkinService.listarPorUsuario(u));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        checkinService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

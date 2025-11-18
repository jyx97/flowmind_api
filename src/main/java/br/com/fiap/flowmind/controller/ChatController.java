package br.com.fiap.flowmind.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.service.AIService;
import br.com.fiap.flowmind.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final AIService aiService;
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> conversar(@RequestBody String mensagem) {
        String resposta = aiService.enviarMensagemChat(mensagem);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/rotina/{idUsuario}")
    public ResponseEntity<String> gerarRotina(@PathVariable Long idUsuario) {

        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        String rotina = aiService.gerarRotinaParaUsuario(usuario);

        return ResponseEntity.ok(rotina);
    }
}

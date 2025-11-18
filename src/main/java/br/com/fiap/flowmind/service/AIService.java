package br.com.fiap.flowmind.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import br.com.fiap.flowmind.model.CheckinDiario;
import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.repository.CheckinRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient;
    private final CheckinRepo checkinRepo;
    private final RotinaService rotinaService;  // <-- ADICIONADO

    public String enviarMensagemChat(String mensagem) {
        return chatClient
                .prompt()
                .user(mensagem)
                .call()
                .content();
    }

    public String gerarRotinaParaUsuario(Usuario usuario) {

        LocalDate hoje = LocalDate.now();
        LocalDate semanaAtras = hoje.minusDays(7);

        List<CheckinDiario> checkins =
                checkinRepo.findByUsuarioAndDataCheckinBetween(usuario, semanaAtras, hoje);

        String prompt;

        if (checkins.isEmpty()) {
            prompt = """
                    Gere uma rotina diária saudável para um usuário sem dados recentes.
                    Responda de forma organizada.
                    """;
        } else {
            StringBuilder resumo = new StringBuilder();
            for (CheckinDiario c : checkins) {
                resumo.append("Dia ").append(c.getDataCheckin())
                      .append(" - Humor: ").append(c.getHumor())
                      .append(", Energia: ").append(c.getEnergia())
                      .append(", Sono: ").append(c.getSono())
                      .append("\n");
            }

            prompt = """
                    Gere uma rotina personalizada com base nos últimos check-ins:

                    %s

                    Inclua:
                    - Horário de acordar
                    - Rotina matinal
                    - Pausas recomendadas
                    - Horários ideais de foco
                    - Hábitos saudáveis
                    - Sono ideal
                    - Atividades antiestresse

                    Responda em formato claro e organizado.
                    """.formatted(resumo.toString());
        }

        String resposta = chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        rotinaService.salvarRotinaDoDia(usuario, resposta);

        return resposta;
    }
}

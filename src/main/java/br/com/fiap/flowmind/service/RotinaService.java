package br.com.fiap.flowmind.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.fiap.flowmind.model.RotinaDiaria;
import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.repository.RotinaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RotinaService {

    private final RotinaRepository repository;

    public RotinaDiaria salvarRotinaDoDia(Usuario usuario, String rotinaJson) {

        LocalDate hoje = LocalDate.now();

        RotinaDiaria rotina = repository.findByUsuarioAndDataRotina(usuario, hoje)
                .orElse(new RotinaDiaria());

        rotina.setUsuario(usuario);
        rotina.setDataRotina(hoje);
        rotina.setRotinaJson(rotinaJson);

        return repository.save(rotina);
    }

    public RotinaDiaria buscarRotinaDoDia(Usuario usuario) {

        LocalDate hoje = LocalDate.now();

        return repository.findByUsuarioAndDataRotina(usuario, hoje)
                .orElseThrow(() ->
                        new EntityNotFoundException("Nenhuma rotina para hoje"));
    }

    public RotinaDiaria atualizarRotinaDoDia(Usuario usuario, String novaRotina) {

    LocalDate hoje = LocalDate.now();
    RotinaDiaria rotina = repository.findByUsuarioAndDataRotina(usuario, hoje)
            .orElseThrow(() -> new EntityNotFoundException("Nenhuma rotina para hoje"));

    rotina.setRotinaJson(novaRotina);

    return repository.save(rotina);
    }

}

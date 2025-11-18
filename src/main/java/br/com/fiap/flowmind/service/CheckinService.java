package br.com.fiap.flowmind.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.flowmind.model.CheckinDiario;
import br.com.fiap.flowmind.model.Usuario;
import br.com.fiap.flowmind.repository.CheckinRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckinService {

    private CheckinRepo repository;

    public CheckinDiario salvar(CheckinDiario checkin) {
        if (checkin.getDataCheckin() == null) {
            checkin.setDataCheckin(LocalDate.now());
        }
        return repository.save(checkin);
    }

    public CheckinDiario buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Check-in não encontrado"));
    }

    public List<CheckinDiario> listarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

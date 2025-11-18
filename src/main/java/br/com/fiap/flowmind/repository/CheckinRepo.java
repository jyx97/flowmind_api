package br.com.fiap.flowmind.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.flowmind.model.CheckinDiario;
import br.com.fiap.flowmind.model.Usuario;

public interface CheckinRepo extends JpaRepository<CheckinDiario, Long> {

    List<CheckinDiario> findByUsuario(Usuario usuario);

    List<CheckinDiario> findByUsuarioAndDataCheckin(Usuario usuario, LocalDate dataCheckin);

    List<CheckinDiario> findByUsuarioOrderByDataCheckinDesc(Usuario usuario);

    List<CheckinDiario> findByUsuarioAndDataCheckinBetween(
            Usuario usuario,
            LocalDate inicio,
            LocalDate fim
    );

}

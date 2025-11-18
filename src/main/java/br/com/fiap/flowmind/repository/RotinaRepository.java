package br.com.fiap.flowmind.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.flowmind.model.RotinaDiaria;
import br.com.fiap.flowmind.model.Usuario;

public interface RotinaRepository extends JpaRepository<RotinaDiaria, Long> {

    Optional<RotinaDiaria> findByUsuarioAndDataRotina(Usuario usuario, LocalDate dataRotina);
}

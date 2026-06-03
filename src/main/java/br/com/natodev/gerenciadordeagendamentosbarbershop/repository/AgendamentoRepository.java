package br.com.natodev.gerenciadordeagendamentosbarbershop.repository;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByDataHora(LocalDateTime dataHora);
}

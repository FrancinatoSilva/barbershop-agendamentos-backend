package br.com.natodev.gerenciadordeagendamentosbarbershop.repository;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
}

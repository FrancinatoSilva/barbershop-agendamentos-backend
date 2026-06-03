package br.com.natodev.gerenciadordeagendamentosbarbershop.repository;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}

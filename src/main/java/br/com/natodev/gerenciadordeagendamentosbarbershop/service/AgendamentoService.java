package br.com.natodev.gerenciadordeagendamentosbarbershop.service;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Agendamento;
import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Cliente;
import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.StatusAgendamento;
import br.com.natodev.gerenciadordeagendamentosbarbershop.repository.AgendamentoRepository;
import br.com.natodev.gerenciadordeagendamentosbarbershop.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteService clienteService;
    private final ServicoService servicoService;

    public Agendamento agendarAtendimento(Agendamento agendamento) {

        clienteService.buscarClientePorId(agendamento.getCliente().getId());
        servicoService.buscarServicoPorId(agendamento.getServico().getId());

        if (agendamentoRepository.existsByDataHora(agendamento.getDataHora())) {
            throw new IllegalArgumentException("Este horário já está reservado. Por favor, escolha outro.");
        }

        agendamento.setStatus(StatusAgendamento.PENDENTE);

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodosAgendamentos() {
        return agendamentoRepository.findAll();
    }
}

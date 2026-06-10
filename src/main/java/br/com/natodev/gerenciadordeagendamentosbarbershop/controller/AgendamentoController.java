package br.com.natodev.gerenciadordeagendamentosbarbershop.controller;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Agendamento;
import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Cliente;
import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Servico;
import br.com.natodev.gerenciadordeagendamentosbarbershop.dto.AgendamentoRequestDTO;
import br.com.natodev.gerenciadordeagendamentosbarbershop.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agendamento agendarAtendimento(@RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {

        Agendamento agendamento = new Agendamento();

        Cliente cliente = new Cliente();
        cliente.setId(agendamentoRequestDTO.clienteId());

        Servico servico = new Servico();
        servico.setId(agendamentoRequestDTO.servicoId());

        agendamento.setCliente(cliente);
        agendamento.setServico(servico);
        agendamento.setDataHora(agendamentoRequestDTO.dataHora());

        return agendamentoService.agendarAtendimento(agendamento);
    }

    @GetMapping
    public List<Agendamento> listarAgendamentos() {
        return agendamentoService.listarTodosAgendamentos();
    }

}

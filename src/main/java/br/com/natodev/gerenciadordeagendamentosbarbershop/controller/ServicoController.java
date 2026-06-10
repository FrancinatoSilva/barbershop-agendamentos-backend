package br.com.natodev.gerenciadordeagendamentosbarbershop.controller;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Servico;
import br.com.natodev.gerenciadordeagendamentosbarbershop.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico criarServico(@RequestBody Servico servico) {
        return servicoService.salvarServico(servico);
    }

    @GetMapping
    public List<Servico> listarServicos() {
        return servicoService.listarServicos();
    }
}
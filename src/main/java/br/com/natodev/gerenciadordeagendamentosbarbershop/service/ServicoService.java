package br.com.natodev.gerenciadordeagendamentosbarbershop.service;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Servico;
import br.com.natodev.gerenciadordeagendamentosbarbershop.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    public Servico buscarServicoPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço com ID " + id +
                        " não foi encontrado"));
    }

}

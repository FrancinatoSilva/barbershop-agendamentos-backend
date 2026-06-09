package br.com.natodev.gerenciadordeagendamentosbarbershop.dto;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        Long clienteId,
        Long servicoId,
        LocalDateTime dataHora
) {
}

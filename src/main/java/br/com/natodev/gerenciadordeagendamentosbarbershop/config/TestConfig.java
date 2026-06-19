package br.com.natodev.gerenciadordeagendamentosbarbershop.config;

import br.com.natodev.gerenciadordeagendamentosbarbershop.domain.Servico;
import br.com.natodev.gerenciadordeagendamentosbarbershop.repository.AgendamentoRepository;
import br.com.natodev.gerenciadordeagendamentosbarbershop.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final ServicoRepository servicoRepository;
    @Override
    public void run(String... args) throws Exception {

        if (servicoRepository.count() == 0) {
            Servico servico1 = new Servico(null, "Corte de Cabelo", new BigDecimal("35.00"), 40);
            Servico servico2 = new Servico(null, "Barba", new BigDecimal("25.00"), 30);
            Servico servico3 = new Servico(null, "Sobrancelha",  new BigDecimal("15.00"), 15);
            Servico servico4 = new Servico(null, "Corte + Barba",  new BigDecimal("55.00"), 70);

            servicoRepository.saveAll(Arrays.asList(servico1, servico2, servico3, servico4));

            System.out.println("Serviços iniciais populados com sucesso!");
        }
    }
}

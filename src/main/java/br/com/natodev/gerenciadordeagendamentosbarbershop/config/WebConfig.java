package br.com.natodev.gerenciadordeagendamentosbarbershop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    //Solucao temporaria para desenvolvimento do front
    public void addResourceMappings(CorsRegistry registry) {

        registry.addMapping("/**");
    }

}

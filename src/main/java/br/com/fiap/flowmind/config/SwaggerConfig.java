package br.com.fiap.flowmind.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FlowMind API")
                        .version("1.0")
                        .description("Documentação da API para o projeto FlowMind — Rotina, ChatBot e Insights de Saúde"));
    }
}

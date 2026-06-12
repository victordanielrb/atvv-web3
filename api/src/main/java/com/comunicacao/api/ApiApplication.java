package com.comunicacao.api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiApplication {

	// A ideia foi seguir a base do projeto feita no excalidraw, e a parte mais
	// "diferente" foi o uso do @Embeddable pra doc e endereco, porque eu nao vi
	// muito sentido em criar uma tabela so pra isso. Ja telefone ficou como
	// @ElementCollection, que por baixo dos panos gera outra tabela por ser uma
	// colecao de valores. Essa organizacao faria ainda mais sentido no postgres,
	// pela possibilidade de usar jsonb.

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}

package com.previdencia.gestaocontribuicao.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Configurações gerais da aplicação.
 * Esta classe contém beans que são usados em toda a aplicação,
 * como o RestTemplate para chamadas REST externas.
 */

@Configuration
public class AppConfig {
    /**
     * Cria e configura um bean do RestTemplate.
     * O RestTemplate é usado para realizar chamadas REST em serviços externos.
     * @return Uma instância do RestTemplate..
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }
}



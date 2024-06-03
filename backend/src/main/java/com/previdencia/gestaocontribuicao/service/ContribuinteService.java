//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.previdencia.gestaocontribuicao.service;

import com.previdencia.gestaocontribuicao.dto.ContribuinteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ContribuinteService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${contribuintes.api.host}")
    private String apiHost;
    @Value("${contribuintes.api.port}")
    private String apiPort;

    public ContribuinteService() {
    }

    public ContribuinteDTO buscarDadosContribuinte(String cpf) throws RestClientException {
        String url = "http://" + this.apiHost + ":" + this.apiPort + "/contribuintes/" + cpf;

        try {
            return (ContribuinteDTO)this.restTemplate.getForObject(url, ContribuinteDTO.class, new Object[0]);
        } catch (RestClientException var4) {
            RestClientException e = var4;
            throw new RestClientException("Nenhum dado encontrado para o CPF: " + cpf, e);
        }
    }
}

package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.dto.ContribuinteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContribuinteService {

    @Autowired
    private RestTemplate restTemplate;

    public ContribuinteDTO buscarDadosContribuinte(String cpf) {

        String url = "http://api_mock_kelvin:8080/contribuintes/" + cpf;
        try {
            ContribuinteDTO contribuinte = restTemplate.getForObject(url, ContribuinteDTO.class);
            System.out.println("Dados do contribuinte recebidos com sucesso: " + contribuinte);
            return contribuinte;
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados do contribuinte: " + e.getMessage());
            return null;
        }
    }
}


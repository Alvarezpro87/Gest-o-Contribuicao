package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.dto.ContribuinteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Serviço para interagir com os dados do contribuinte.
 * Realiza operações para buscar informações de contribuintes a partir da Api de Gestão Contribuintes.
 */
@Service
public class ContribuinteService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${contribuintes.api.host}")
    private String apiHost;

    @Value("${contribuintes.api.port}")
    private String apiPort;


    /**
     * Busca dados de um contribuinte utilizando seu CPF.
     *
     * @param cpf O CPF do contribuinte a ser buscado.
     * @return Um objeto {@link ContribuinteDTO} contendo os dados do contribuinte ou {@code null} se ocorrer um erro.
     */
    public ContribuinteDTO buscarDadosContribuinte(String cpf) {

        String url = "http://" + apiHost + ":" + apiPort + "/contribuintes/" + cpf;
        try {
            ContribuinteDTO contribuinte = restTemplate.getForObject(url, ContribuinteDTO.class);
            System.out.println("Dados do contribuinte recebidos com sucesso: " + contribuinte);
            return contribuinte;
        } catch (Exception serviceExcception) {
            throw new RuntimeException("Falha ao recuperar dados do contribuinte: " + serviceExcception.getMessage());
        }
    }
}


package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.dto.ContribuinteDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
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
     * @return Um objeto {@link ContribuinteDTO} contendo os dados do contribuinte.
     * @throws RestClientException Se ocorrer um erro ao acessar a API externa.
     */

    public ContribuinteDTO buscarDadosContribuinte(String cpf) throws RestClientException {
        String url = "http://" + apiHost + ":" + apiPort + "/contribuintes/" + cpf;
        try {
            ContribuinteDTO contribuinte = restTemplate.getForObject(url, ContribuinteDTO.class);
            return contribuinte;

        } catch (RestClientException e) {
            throw new RestClientException("Nenhum dado encontrado para o CPF: " + cpf, e);
        }
    }
}





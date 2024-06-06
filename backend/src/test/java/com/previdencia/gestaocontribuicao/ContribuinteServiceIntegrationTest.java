package com.previdencia.gestaocontribuicao;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.previdencia.gestaocontribuicao.dto.ContribuinteDTO;
import com.previdencia.gestaocontribuicao.service.ContribuinteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ContribuinteServiceIntegrationTest {

    @Autowired
    private ContribuinteService contribuinteService;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8081));
        wireMockServer.start();
        WireMock.configureFor("192.168.37.10", 8081);

        stubFor(get(urlEqualTo("/contribuintes/84688392052"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"info\": { \"cpf\": \"84688392052\", \"categoria\": \"MEI\", \"salario\": 7500.00, \"inicioContribuicao\": \"01-01-2020\" } }")));
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testBuscarDadosContribuinte() {
        ContribuinteDTO contribuinteDTO = contribuinteService.buscarDadosContribuinte("84688392052");
        assertEquals("84688392052", contribuinteDTO.getInfo().getCpf());
        assertEquals("MEI", contribuinteDTO.getInfo().getCategoria());
        assertEquals(new BigDecimal("7500.00"), contribuinteDTO.getInfo().getSalario());
        assertEquals(LocalDate.of(2020, 1, 1), contribuinteDTO.getInfo().getInicioContribuicao());
    }
}

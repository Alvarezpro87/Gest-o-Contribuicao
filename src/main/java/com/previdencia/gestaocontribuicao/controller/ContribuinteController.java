package com.previdencia.gestaocontribuicao.controller;
import com.previdencia.gestaocontribuicao.dto.ContribuinteDTO;
import com.previdencia.gestaocontribuicao.service.ContribuinteService;
import com.previdencia.gestaocontribuicao.service.SalarioMinimoService;
import com.previdencia.gestaocontribuicao.service.AliquotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contribuintes")
public class ContribuinteController {
    @Autowired
    private ContribuinteService contribuinteService;
    @Autowired
    private SalarioMinimoService salarioMinimoService;
    @Autowired
    private AliquotaService aliquotaService;


    @GetMapping("/consultar/{cpf}")
    public ResponseEntity<?> consultarContribuinte(@PathVariable String cpf) {
        ContribuinteDTO contribuinte = contribuinteService.buscarDadosContribuinte(cpf);
        LocalDate inicioContribuicao = contribuinte.getInicio_contribuicao();
        long mesesContribuicao = ChronoUnit.MONTHS.between(inicioContribuicao, LocalDate.now());

        BigDecimal aliquota = aliquotaService.buscarAliquotaPorCategoriaESalario(contribuinte.getCategoria(), contribuinte.getSalario());
        BigDecimal salarioMinimoInicio = salarioMinimoService.buscarValorSalarioMinimoParaData(inicioContribuicao);
        BigDecimal salarioMinimoAtual = salarioMinimoService.buscarValorSalarioMinimoParaData(LocalDate.now());

        BigDecimal valorContribuicaoMensal = contribuinte.getSalario().multiply(aliquota.divide(new BigDecimal("100")));
        BigDecimal totalContribuido = valorContribuicaoMensal.multiply(new BigDecimal(mesesContribuicao));
        BigDecimal totalContribuidoAjustado = totalContribuido.multiply(salarioMinimoAtual).divide(salarioMinimoInicio, 2, RoundingMode.HALF_UP);

        // Estrutura de resposta
        return ResponseEntity.ok(Map.of(
                "cpf", contribuinte.getCpf(),
                "categoria", contribuinte.getCategoria(),
                "tempoContribuicaoMeses", mesesContribuicao,
                "totalContribuidoAjustado", totalContribuidoAjustado
        ));
    }

}


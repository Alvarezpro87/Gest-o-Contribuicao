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
import java.util.Map;
// Expõe os endpoints para consultar informações do contribuint com base no CPF adquirido na API KEVIN
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
        ContribuinteDTO contribuinte = contribuinteService.buscarDadosContribuinte(cpf); //Busca os dados do contribuinte usando o CPF e retorna o objeto Contribuinte DTO
        LocalDate inicioContribuicao = contribuinte.getInicio_contribuicao(); //Diferça entre a data de inicio da contribuição e a data atual
        long mesesContribuicao = ChronoUnit.MONTHS.between(inicioContribuicao, LocalDate.now());

        BigDecimal aliquota = aliquotaService.buscarAliquotaPorCategoriaESalario(contribuinte.getCategoria(), contribuinte.getSalario());//Busca a aliquota por categoria e salário do contribuinte
        BigDecimal salarioMinimoInicio = salarioMinimoService.buscarValorSalarioMinimoParaData(inicioContribuicao);//Busca o valor do salário mínimo na data de início da contribuição e o valor atual do salário mínimo.
        BigDecimal salarioMinimoAtual = salarioMinimoService.buscarValorSalarioMinimoParaData(LocalDate.now());

        BigDecimal valorContribuicaoMensal = contribuinte.getSalario().multiply(aliquota.divide(new BigDecimal("100")));//Calcula o valor mensal da contriubição * o percentual da aliquota
        BigDecimal totalContribuido = valorContribuicaoMensal.multiply(new BigDecimal(mesesContribuicao));//Multiplica o valor pelo total de meses da contribuição
        BigDecimal totalContribuidoAjustado = totalContribuido.multiply(salarioMinimoAtual).divide(salarioMinimoInicio, 2, RoundingMode.HALF_UP); //Ajusta o valor total pelo atual do salario mínimo para refletir a valoriazação da moeda.

        // Estrutura de resposta
        return ResponseEntity.ok(Map.of(
                "cpf", contribuinte.getCpf(),
                "categoria", contribuinte.getCategoria(),
                "tempoContribuicaoMeses", mesesContribuicao,
                "totalContribuidoAjustado", totalContribuidoAjustado
        ));
    }

}


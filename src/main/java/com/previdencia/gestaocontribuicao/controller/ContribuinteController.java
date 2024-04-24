package com.previdencia.gestaocontribuicao.controller;
import com.previdencia.gestaocontribuicao.dto.ContribuinteDTO;
import com.previdencia.gestaocontribuicao.service.ContribuinteService;
import com.previdencia.gestaocontribuicao.service.SalarioMinimoService;
import com.previdencia.gestaocontribuicao.service.AliquotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Operation(summary = "Consulta as informações do contribuinte com base no CPF",
            description = "Retorna informações detalhadas sobre o contribuinte, incluindo categoria, tempo de contribuição e total contribuído ajustado.")
    @ApiResponse(responseCode = "200", description = "Informações do contribuinte encontradas com sucesso")
    @ApiResponse(responseCode = "404", description = "Contribuinte não encontrado")


    @GetMapping("/consultar/{cpf}")
    public ResponseEntity<?> consultarContribuinte(@PathVariable String cpf) {
        // Validação do CPF
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return ResponseEntity.badRequest().body("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }

        ContribuinteDTO contribuinteDTO = contribuinteService.buscarDadosContribuinte(cpf);
        if (contribuinteDTO == null || contribuinteDTO.getInfo() == null) {
            return ResponseEntity.notFound().build();
        }

        ContribuinteDTO.Info info = contribuinteDTO.getInfo();
        LocalDate inicioContribuicao = info.getInicioContribuicao();
        if (inicioContribuicao == null) {
            return ResponseEntity.badRequest().body("Data de início da contribuição não informada.");
        }

        long mesesContribuicao = ChronoUnit.MONTHS.between(inicioContribuicao, LocalDate.now());
        BigDecimal aliquota = aliquotaService.buscarAliquotaPorCategoriaESalario(info.getCategoria(), info.getSalario());
        BigDecimal salarioMinimoInicio = salarioMinimoService.buscarValorSalarioMinimoParaData(inicioContribuicao);
        BigDecimal salarioMinimoAtual = salarioMinimoService.buscarValorSalarioMinimoParaData(LocalDate.now());

        BigDecimal valorContribuicaoMensal = info.getSalario().multiply(aliquota.divide(new BigDecimal("100")));
        BigDecimal totalContribuido = valorContribuicaoMensal.multiply(new BigDecimal(mesesContribuicao));
        BigDecimal totalContribuidoAjustado = totalContribuido.multiply(salarioMinimoAtual).divide(salarioMinimoInicio, 2, RoundingMode.HALF_UP);

        Map<String, Object> response = Map.of(
                "cpf", info.getCpf(),
                "categoria", info.getCategoria(),
                "tempoContribuicaoMeses", mesesContribuicao,
                "totalContribuidoAjustado", totalContribuidoAjustado
        );

        return ResponseEntity.ok(response);
    }
}



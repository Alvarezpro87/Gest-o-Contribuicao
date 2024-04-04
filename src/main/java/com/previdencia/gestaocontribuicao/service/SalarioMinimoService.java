package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.model.SalarioMinimoHistorico;
import com.previdencia.gestaocontribuicao.repository.SalarioMinimoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Serviço para manipulação e consulta de informações relacionadas ao salário mínimo histórico.
 */
@Service
public class SalarioMinimoService {


    @Autowired
    private SalarioMinimoHistoricoRepository salarioMinimoHistoricoRepository;

    /**
     * Busca o valor do salário mínimo para uma data especificada.
     * Retorna o valor do salário mínimo mais recente até a data fornecida.
     *
     * @param data A data para a qual o valor do salário mínimo é solicitado.
     * @return O valor do salário mínimo para a data especificada.
     * @throws RuntimeException Se não for encontrado um valor de salário mínimo para a data especificada.
     */
    public BigDecimal buscarValorSalarioMinimoParaData(LocalDate data) {
        return salarioMinimoHistoricoRepository
                .findTopByDataMinimoLessThanEqualOrderByDataMinimoDesc(data)
                .map(SalarioMinimoHistorico::getValorSalarioMinimoAno)
                .orElseThrow(() -> new RuntimeException("Salário mínimo não encontrado para a data especificada."));
    }

}



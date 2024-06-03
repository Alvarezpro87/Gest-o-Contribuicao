//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.previdencia.gestaocontribuicao.service;

import com.previdencia.gestaocontribuicao.model.SalarioMinimoHistorico;
import com.previdencia.gestaocontribuicao.repository.SalarioMinimoHistoricoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalarioMinimoService {
    @Autowired
    private SalarioMinimoHistoricoRepository salarioMinimoHistoricoRepository;

    public SalarioMinimoService() {
    }

    public BigDecimal buscarValorSalarioMinimoParaData(LocalDate data) {
        return (BigDecimal)this.salarioMinimoHistoricoRepository.findTopByDataMinimoLessThanEqualOrderByDataMinimoDesc(data).map(SalarioMinimoHistorico::getValorSalarioMinimoAno).orElseThrow(() -> {
            return new RuntimeException("Salário mínimo não encontrado para a data especificada.");
        });
    }
}

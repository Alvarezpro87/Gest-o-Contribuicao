package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.model.SalarioMinimoHistorico;
import com.previdencia.gestaocontribuicao.repository.SalarioMinimoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class SalarioMinimoService {

    @Autowired
    private SalarioMinimoHistoricoRepository salarioMinimoHistoricoRepository;

    public BigDecimal buscarValorSalarioMinimoParaData(LocalDate data) {
        return salarioMinimoHistoricoRepository
                .findTopByDataMinimoLessThanEqualOrderByDataMinimoDesc(data)
                .map(SalarioMinimoHistorico::getValorSalarioMinimoAno)
                .orElseThrow(() -> new RuntimeException("Salário mínimo não encontrado para a data especificada."));
    }

}



package com.previdencia.gestaocontribuicao.repository;

import com.previdencia.gestaocontribuicao.model.SalarioMinimoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface SalarioMinimoHistoricoRepository extends JpaRepository<SalarioMinimoHistorico, Long> {
    Optional<SalarioMinimoHistorico> findByDataMinimo(LocalDate dataMinimo);

    Optional<SalarioMinimoHistorico> findTopByOrderByDataMinimoDesc();


    Optional<SalarioMinimoHistorico> findTopByDataMinimoLessThanEqualOrderByDataMinimoDesc(LocalDate data);
}


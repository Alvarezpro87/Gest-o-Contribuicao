package com.previdencia.gestaocontribuicao.repository;

/**
 * Repositório para operações CRUD em entidades {@link SalarioMinimoHistorico}.
 * Inclui métodos personalizados para buscar valores de salário mínimo histórico.
 */

import com.previdencia.gestaocontribuicao.model.SalarioMinimoHistorico;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarioMinimoHistoricoRepository extends JpaRepository<SalarioMinimoHistorico, Long> {
    Optional<SalarioMinimoHistorico> findByDataMinimo(LocalDate dataMinimo);

    Optional<SalarioMinimoHistorico> findTopByOrderByDataMinimoDesc();

    Optional<SalarioMinimoHistorico> findTopByDataMinimoLessThanEqualOrderByDataMinimoDesc(LocalDate data);
}



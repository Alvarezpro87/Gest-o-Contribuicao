package com.previdencia.gestaocontribuicao.repository;

/**
 * Repositório para operações CRUD em entidades {@link SalarioMinimoHistorico}.
 * Inclui métodos personalizados para buscar valores de salário mínimo histórico.
 */

import com.previdencia.gestaocontribuicao.model.SalarioMinimoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface SalarioMinimoHistoricoRepository extends JpaRepository<SalarioMinimoHistorico, Long> {

    /**
     * Encontra o {@link SalarioMinimoHistorico} para uma data específica.
     *
     * @param dataMinimo A data para a qual o salário mínimo é buscado.
     * @return Um {@link Optional} contendo o salário mínimo encontrado, ou vazio se nenhum for encontrado.
     */
    Optional<SalarioMinimoHistorico> findByDataMinimo(LocalDate dataMinimo);

    /**
     * Encontra o registro mais recente de salário mínimo.
     *
     * @return Um {@link Optional} contendo o registro mais recente do salário mínimo, ou vazio se nenhum for encontrado.
     */
    Optional<SalarioMinimoHistorico> findTopByOrderByDataMinimoDesc();

    /**
     * Encontra o valor de salário mínimo mais recente para uma data ou antes dela.
     *
     * @param data A data limite para a busca do valor do salário mínimo.
     * @return Um {@link Optional} contendo o salário mínimo histórico encontrado, ou vazio se nenhum for encontrado.
     */
    Optional<SalarioMinimoHistorico> findTopByDataMinimoLessThanEqualOrderByDataMinimoDesc(LocalDate data);
}


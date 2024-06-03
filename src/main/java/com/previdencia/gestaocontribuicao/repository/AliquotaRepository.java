package com.previdencia.gestaocontribuicao.repository;

import com.previdencia.gestaocontribuicao.model.Aliquota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repositório para operações CRUD em entidades {@link Aliquota}.
 * Fornece métodos adicionais para buscar alíquotas por categoria.
 */
public interface AliquotaRepository extends JpaRepository<Aliquota, Long> {

    /**
     * Busca uma lista de {@link Aliquota} pela categoria especificada, ignorando maiúsculas e minúsculas.
     *
     * @param categoria O nome da categoria de contribuição.
     * @return Uma lista de alíquotas que pertencem à categoria especificada.
     */
    @Query("SELECT a FROM Aliquota a WHERE LOWER(a.categoria) = LOWER(:categoria)")
    List<Aliquota> findByCategoriaIgnoreCase(@Param("categoria") String categoria);
}

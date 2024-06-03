//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.previdencia.gestaocontribuicao.repository;

import com.previdencia.gestaocontribuicao.model.Aliquota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AliquotaRepository extends JpaRepository<Aliquota, Long> {
    @Query("SELECT a FROM Aliquota a WHERE LOWER(a.categoria) = LOWER(:categoria)")
    List<Aliquota> findByCategoriaIgnoreCase(@Param("categoria") String categoria);
}

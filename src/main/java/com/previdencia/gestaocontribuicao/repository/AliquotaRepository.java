package com.previdencia.gestaocontribuicao.repository;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AliquotaRepository extends JpaRepository<Aliquota, Long> {
    List<Aliquota> findByCategoria(String categoria);
}

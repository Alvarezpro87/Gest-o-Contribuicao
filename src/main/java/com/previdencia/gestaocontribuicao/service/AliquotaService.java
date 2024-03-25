package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import com.previdencia.gestaocontribuicao.repository.AliquotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

//Ponte entre a camada "AliquotaRepository" e o controlador
@Service
public class AliquotaService {

    @Autowired
    private AliquotaRepository aliquotaRepository;

    public Aliquota criarAliquota(AliquotaDTO aliquotaDTO) {
        Aliquota aliquota = new Aliquota(null, aliquotaDTO.getCategoria(), aliquotaDTO.getSalarioInicio(), aliquotaDTO.getSalarioFim(), aliquotaDTO.getValorAliquota());
        return aliquotaRepository.save(aliquota);
    }

    public List<Aliquota> listarTodas() {
        return aliquotaRepository.findAll();
    }

    public Aliquota buscarPorId(Long id) {
        return aliquotaRepository.findById(id).orElseThrow(() -> new RuntimeException("Alíquota não encontrada"));
    }

    public Aliquota atualizarAliquota(Long id, AliquotaDTO aliquotaDTO) {
        Aliquota aliquota = buscarPorId(id);
        aliquota.setCategoria(aliquotaDTO.getCategoria());
        aliquota.setSalarioInicio(aliquotaDTO.getSalarioInicio());
        aliquota.setSalarioFim(aliquotaDTO.getSalarioFim());
        aliquota.setValorAliquota(aliquotaDTO.getValorAliquota());
        return aliquotaRepository.save(aliquota);
    }
    public BigDecimal buscarAliquotaPorCategoriaESalario(String categoria, BigDecimal salario) {
        return aliquotaRepository.findByCategoria(categoria)
                .stream()
                .filter(aliquota -> salario.compareTo(aliquota.getSalarioInicio()) >= 0 && salario.compareTo(aliquota.getSalarioFim()) <= 0)
                .findFirst()
                .map(Aliquota::getValorAliquota)
                .orElseThrow(() -> new RuntimeException("Alíquota não encontrada para a categoria: " + categoria + " e salário: " + salario));
    }


    public void deletarAliquota(Long id) {
        aliquotaRepository.deleteById(id);
    }
}

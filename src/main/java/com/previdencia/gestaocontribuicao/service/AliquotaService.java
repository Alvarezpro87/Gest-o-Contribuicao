package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import com.previdencia.gestaocontribuicao.repository.AliquotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;


/**
 * Serviço para gerenciar alíquotas.
 */
@Service
public class AliquotaService {

    @Autowired
    private AliquotaRepository aliquotaRepository;

    /**
     * Cria uma nova alíquota com base nos dados fornecidos.
     *
     * @param aliquotaDTO Dados da alíquota a ser criada.
     * @return A alíquota criada.
     */
    public Aliquota criarAliquota(AliquotaDTO aliquotaDTO) {
        try {
            Aliquota aliquota = new Aliquota(null, aliquotaDTO.getCategoria(), aliquotaDTO.getSalarioInicio(), aliquotaDTO.getSalarioFim(), aliquotaDTO.getValorAliquota());
            return aliquotaRepository.save(aliquota);
        } catch (DataIntegrityViolationException violacaoIntegridade) {
            throw new RuntimeException("Aliquota duplicada tente novamente: " + violacaoIntegridade.getMessage());
        }
    }

    /**
     * Lista todas as alíquotas cadastradas.
     *
     * @return Lista de alíquotas.
     */
    public List<Aliquota> listarTodas() {
        return aliquotaRepository.findAll();
    }

    /**
     * Busca uma alíquota pelo seu identificador.
     *
     * @param id Identificador da alíquota.
     * @return A alíquota encontrada.
     * @throws RuntimeException Se a alíquota não for encontrada.
     */
    public Aliquota buscarPorId(Long id) {
        return aliquotaRepository.findById(id).orElseThrow(() -> new RuntimeException("Alíquota não encontrada"));
    }

    /**
     * Atualiza os dados de uma alíquota existente.
     *
     * @param id Identificador da alíquota a ser atualizada.
     * @param aliquotaDTO Novos dados para a alíquota.
     * @return A alíquota atualizada.
     */
    public Aliquota atualizarAliquota(Long id, AliquotaDTO aliquotaDTO) {
        Aliquota aliquota = buscarPorId(id);
        aliquota.setCategoria(aliquotaDTO.getCategoria());
        aliquota.setSalarioInicio(aliquotaDTO.getSalarioInicio());
        aliquota.setSalarioFim(aliquotaDTO.getSalarioFim());
        aliquota.setValorAliquota(aliquotaDTO.getValorAliquota());
        return aliquotaRepository.save(aliquota);
    }

    /**
     * Busca a alíquota aplicável com base na categoria e salário do contribuinte.
     *
     * @param categoria Categoria do contribuinte.
     * @param salario Salário do contribuinte.
     * @return O valor da alíquota aplicável.
     * @throws RuntimeException Se não encontrar uma alíquota aplicável.
     */
    public BigDecimal buscarAliquotaPorCategoriaESalario(String categoria, BigDecimal salario) {
        return aliquotaRepository.findByCategoria(categoria)
                .stream()
                .filter(aliquota -> salario.compareTo(aliquota.getSalarioInicio()) >= 0 && salario.compareTo(aliquota.getSalarioFim()) <= 0)
                .findFirst()
                .map(Aliquota::getValorAliquota)
                .orElseThrow(() -> new RuntimeException("Alíquota não encontrada para a categoria: " + categoria + " e salário: " + salario));
    }

    /**
     * Deleta uma alíquota pelo seu identificador.
     *
     * @param id Identificador da alíquota a ser deletada.
     */
    public void deletarAliquota(Long id) {
        aliquotaRepository.deleteById(id);
    }
}

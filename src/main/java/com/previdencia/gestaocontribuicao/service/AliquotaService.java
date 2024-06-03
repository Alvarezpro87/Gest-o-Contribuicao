package com.previdencia.gestaocontribuicao.service;
import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import com.previdencia.gestaocontribuicao.repository.AliquotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

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
        String categoria = capitalize(aliquotaDTO.getCategoria());
        BigDecimal salarioInicio = parseAndFormatarSalario(aliquotaDTO.getSalarioInicio().toString());
        BigDecimal salarioFim = parseAndFormatarSalario(aliquotaDTO.getSalarioFim().toString());
        BigDecimal valorAliquota = formatarAliquota(aliquotaDTO.getValorAliquota());

        Aliquota aliquota = new Aliquota(null, categoria, salarioInicio, salarioFim, valorAliquota);
        return aliquotaRepository.save(aliquota);
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
        return aliquotaRepository.findById(id).orElseThrow(() -> new RuntimeException("Alíquota não encontrada para o ID: " + id));
    }

    /**
     * Busca uma alíquota pela categoria.
     *
     * @param categoria Categoria da alíquota.
     * @return A alíquota encontrada.
     * @throws RuntimeException Se a alíquota não for encontrada.
     */
    public Aliquota buscarPorCategoria(String categoria) {
        return aliquotaRepository.findByCategoriaIgnoreCase(categoria).stream().findFirst().orElseThrow(() -> new RuntimeException("Alíquota não encontrada para a categoria: " + categoria));
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
        aliquota.setCategoria(capitalize(aliquotaDTO.getCategoria()));
        aliquota.setSalarioInicio(parseAndFormatarSalario(aliquotaDTO.getSalarioInicio().toString()));
        aliquota.setSalarioFim(parseAndFormatarSalario(aliquotaDTO.getSalarioFim().toString()));
        aliquota.setValorAliquota(formatarAliquota(aliquotaDTO.getValorAliquota()));
        return aliquotaRepository.save(aliquota);
    }

    /**
     * Busca a alíquota aplicável com base na categoria e salário do contribuinte, ignorando maiúsculas e minúsculas.
     *
     * @param categoria Categoria do contribuinte.
     * @param salario Salário do contribuinte.
     * @return O valor da alíquota aplicável.
     * @throws RuntimeException Se não encontrar uma alíquota aplicável.
     */
    public BigDecimal buscarAliquotaPorCategoriaESalario(String categoria, BigDecimal salario) {
        return aliquotaRepository.findByCategoriaIgnoreCase(categoria)
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

    /**
     * Deleta uma alíquota pela categoria, ignorando maiúsculas e minúsculas.
     *
     * @param categoria Categoria da alíquota a ser deletada.
     */
    public void deletarAliquotaPorCategoria(String categoria) {
        List<Aliquota> aliquotas = aliquotaRepository.findByCategoriaIgnoreCase(categoria);
        if (aliquotas.isEmpty()) {
            throw new RuntimeException("Alíquota não encontrada para a categoria: " + categoria);
        }
        aliquotaRepository.deleteAll(aliquotas);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    private BigDecimal parseAndFormatarSalario(String salarioStr) {
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat format = new DecimalFormat("#,##0.00", symbols);
            format.setParseBigDecimal(true);
            BigDecimal salario = (BigDecimal) format.parse(salarioStr.replace(".", "").replace(",", "."));
            return salario.setScale(2, RoundingMode.HALF_UP);
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao formatar o salário: " + salarioStr, e);
        }
    }

    private BigDecimal formatarAliquota(BigDecimal aliquota) {
        return aliquota.setScale(1, RoundingMode.HALF_UP);
    }
}

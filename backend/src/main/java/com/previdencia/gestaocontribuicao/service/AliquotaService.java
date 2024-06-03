
package com.previdencia.gestaocontribuicao.service;

import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import com.previdencia.gestaocontribuicao.repository.AliquotaRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliquotaService {
    @Autowired
    private AliquotaRepository aliquotaRepository;

    public AliquotaService() {
    }

    public Aliquota criarAliquota(AliquotaDTO aliquotaDTO) {
        String categoria = this.capitalize(aliquotaDTO.getCategoria());
        BigDecimal salarioInicio = this.parseAndFormatarSalario(aliquotaDTO.getSalarioInicio().toString());
        BigDecimal salarioFim = this.parseAndFormatarSalario(aliquotaDTO.getSalarioFim().toString());
        BigDecimal valorAliquota = this.formatarAliquota(aliquotaDTO.getValorAliquota());
        Aliquota aliquota = new Aliquota((Long)null, categoria, salarioInicio, salarioFim, valorAliquota);
        return (Aliquota)this.aliquotaRepository.save(aliquota);
    }

    public List<Aliquota> listarTodas() {
        return this.aliquotaRepository.findAll();
    }

    public Aliquota buscarPorId(Long id) {
        return (Aliquota)this.aliquotaRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Alíquota não encontrada para o ID: " + id);
        });
    }

    public Aliquota buscarPorCategoria(String categoria) {
        return (Aliquota)this.aliquotaRepository.findByCategoriaIgnoreCase(categoria).stream().findFirst().orElseThrow(() -> {
            return new RuntimeException("Alíquota não encontrada para a categoria: " + categoria);
        });
    }

    public Aliquota atualizarAliquota(Long id, AliquotaDTO aliquotaDTO) {
        Aliquota aliquota = this.buscarPorId(id);
        aliquota.setCategoria(this.capitalize(aliquotaDTO.getCategoria()));
        aliquota.setSalarioInicio(this.parseAndFormatarSalario(aliquotaDTO.getSalarioInicio().toString()));
        aliquota.setSalarioFim(this.parseAndFormatarSalario(aliquotaDTO.getSalarioFim().toString()));
        aliquota.setValorAliquota(this.formatarAliquota(aliquotaDTO.getValorAliquota()));
        return (Aliquota)this.aliquotaRepository.save(aliquota);
    }

    public BigDecimal buscarAliquotaPorCategoriaESalario(String categoria, BigDecimal salario) {
        return (BigDecimal)this.aliquotaRepository.findByCategoriaIgnoreCase(categoria).stream().filter((aliquota) -> {
            return salario.compareTo(aliquota.getSalarioInicio()) >= 0 && salario.compareTo(aliquota.getSalarioFim()) <= 0;
        }).findFirst().map(Aliquota::getValorAliquota).orElseThrow(() -> {
            return new RuntimeException("Alíquota não encontrada para a categoria: " + categoria + " e salário: " + String.valueOf(salario));
        });
    }

    public void deletarAliquota(Long id) {
        this.aliquotaRepository.deleteById(id);
    }

    public void deletarAliquotaPorCategoria(String categoria) {
        List<Aliquota> aliquotas = this.aliquotaRepository.findByCategoriaIgnoreCase(categoria);
        if (aliquotas.isEmpty()) {
            throw new RuntimeException("Alíquota não encontrada para a categoria: " + categoria);
        } else {
            this.aliquotaRepository.deleteAll(aliquotas);
        }
    }

    private String capitalize(String str) {
        if (str != null && !str.isEmpty()) {
            String var10000 = str.substring(0, 1).toUpperCase();
            return var10000 + str.substring(1).toLowerCase();
        } else {
            return str;
        }
    }

    private BigDecimal parseAndFormatarSalario(String salarioStr) {
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat format = new DecimalFormat("#,##0.00", symbols);
            format.setParseBigDecimal(true);
            BigDecimal salario = (BigDecimal)format.parse(salarioStr.replace(".", "").replace(",", "."));
            return salario.setScale(2, RoundingMode.HALF_UP);
        } catch (ParseException var5) {
            ParseException e = var5;
            throw new RuntimeException("Erro ao formatar o salário: " + salarioStr, e);
        }
    }

    private BigDecimal formatarAliquota(BigDecimal aliquota) {
        return aliquota.setScale(1, RoundingMode.HALF_UP);
    }
}

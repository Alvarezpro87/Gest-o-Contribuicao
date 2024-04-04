package com.previdencia.gestaocontribuicao.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate; // Importe a classe LocalDate.

/**
 * Representa o histórico de valores do salário mínimo.
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalarioMinimoHistorico {

    /**
     * Identificador único do registro de salário mínimo.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Data de início da validade deste valor de salário mínimo.
     */

    @Column(nullable = false)
    private LocalDate dataMinimo;

    /**
     * Valor do salário mínimo para o ano especificado em dataMinimo.
     */

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_salario_minimo_ano;

    /**
     * Retorna o valor do salário mínimo para o ano especificado.
     *
     * @return O valor do salário mínimo.
     */

    public BigDecimal getValorSalarioMinimoAno() {

        return this.valor_salario_minimo_ano;
    }
}






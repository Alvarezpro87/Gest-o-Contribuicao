package com.previdencia.gestaocontribuicao.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate; // Importe a classe LocalDate.

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalarioMinimoHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataMinimo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor_salario_minimo_ano;

    // Método para retornar o valor do salário mínimo na data especificada.
    public BigDecimal getValorSalarioMinimoAno() {
        return this.valor_salario_minimo_ano;
    }
}

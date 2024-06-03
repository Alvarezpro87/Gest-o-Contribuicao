package com.previdencia.gestaocontribuicao.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Representa uma alíquota aplicada sobre o salário de um contribuinte.
 */


@Table(name="aliquota")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Aliquota {

    /**
     * Identificador único da alíquota.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Categoria do contribuinte a qual a alíquota se aplica.
     */

    @Column(nullable = false)
    private String categoria;

    /**
     * Salário inicial para aplicação da alíquota.
     */

    @Column(name = "salario_inicio", nullable = false)
    private BigDecimal salarioInicio;

    /**
     * Salário final para aplicação da alíquota.
     */

    @Column(name = "salario_fim", nullable = false)
    private BigDecimal salarioFim;

    /**
     * Valor percentual da alíquota.
     */

    @Column(name = "valor_aliquota", nullable = false)
    private BigDecimal valorAliquota;


}

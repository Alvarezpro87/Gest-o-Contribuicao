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
@Table(name="aliquota")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Aliquota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoria;

    @Column(name = "salario_inicio", nullable = false)
    private BigDecimal salarioInicio;

    @Column(name = "salario_fim", nullable = false)
    private BigDecimal salarioFim;

    @Column(name = "valor_aliquota", nullable = false)
    private BigDecimal valorAliquota;


}

package com.previdencia.gestaocontribuicao.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AliquotaDTO {
    private String categoria;
    private BigDecimal salarioInicio;
    private BigDecimal salarioFim;
    private BigDecimal valorAliquota;
}

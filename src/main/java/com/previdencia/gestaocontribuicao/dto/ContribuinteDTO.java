package com.previdencia.gestaocontribuicao.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContribuinteDTO {
    private String cpf;
    private String categoria;
    private BigDecimal salario;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate inicio_contribuicao;

}

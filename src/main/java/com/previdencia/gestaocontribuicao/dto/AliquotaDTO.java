package com.previdencia.gestaocontribuicao.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
//Usado para transferir dados entre diferentes camadas da aplicação.

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Representa os dados para criação ou atualização de uma alíquota.")
public class AliquotaDTO {

    @Schema(description = "Categoria da alíquota", example = "Militar", required = true)
    private String categoria;

    @Schema(description = "Salário inicial da faixa salarial da alíquota", example = "1412.00", required = true)
    private BigDecimal salarioInicio;

    @Schema(description = "Salário final da faixa salarial da alíquota", example = "7000.00", required = true)
    private BigDecimal salarioFim;

    @Schema(description = "Valor percentual da alíquota", example = "5", required = true)
    private BigDecimal valorAliquota;
}
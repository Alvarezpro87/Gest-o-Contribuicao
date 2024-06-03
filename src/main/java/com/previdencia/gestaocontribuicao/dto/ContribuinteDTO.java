package com.previdencia.gestaocontribuicao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa os dados de um contribuinte.")
public class ContribuinteDTO {
    @JsonProperty("info")
    private Info info;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        @Schema(description = "CPF do contribuinte", example = "12345678901", required = true)
        @CPF(message = "CPF deve conter 11 números")
        @JsonProperty("cpf")
        private String cpf;

        @Schema(description = "Categoria do contribuinte", example = "Empregado", required = true)
        @JsonProperty("categoria")
        private String categoria;

        @Schema(description = "Salário do contribuinte", example = "5000.00", required = true)
        @JsonProperty("salario")
        private BigDecimal salario;

        @Schema(description = "Data de início da contribuição", example = "2020/01/01", required = true)
        @JsonFormat(pattern = "yyyy/MM/dd")
        @JsonProperty("inicioContribuicao")
        private LocalDate inicioContribuicao;
    }
}

package com.previdencia.gestaocontribuicao.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
//Usado para transferir dados entre diferentes camadas da aplicação.

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa os dados de um contribuinte.")
public class ContribuinteDTO {

    @Schema(description = "CPF do contribuinte", example = "12345678901", required = true)
    private String cpf;

    @Schema(description = "Categoria do contribuinte", example = "Empregado", required = true)
    private String categoria;

    @Schema(description = "Salário do contribuinte", example = "5000.00", required = true)
    private BigDecimal salario;

    @Schema(description = "Data de início da contribuição", example = "01/01/2020", required = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate inicioContribuicao;
}

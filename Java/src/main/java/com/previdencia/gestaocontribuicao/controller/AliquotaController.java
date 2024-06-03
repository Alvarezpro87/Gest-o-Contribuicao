package com.previdencia.gestaocontribuicao.controller;

import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import com.previdencia.gestaocontribuicao.service.AliquotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aliquotas")
public class AliquotaController {

    @Autowired
    private AliquotaService aliquotaService;

    @Operation(summary = "Cria uma nova aliquota", description = "Cria uma nova aliquota com as informações fornecidas.")
    @ApiResponse(responseCode = "200", description = "Aliquota criada com sucesso")
    @PostMapping
    public ResponseEntity<?> criarAliquota(@RequestBody AliquotaDTO aliquotaDTO) {
        try {
            Aliquota novaAliquota = aliquotaService.criarAliquota(aliquotaDTO);
            return ResponseEntity.ok(novaAliquota);
        } catch (RuntimeException AliquotaDuplicada) {
            String MensagemErro = "Aliquota duplicada: " + AliquotaDuplicada.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MensagemErro);
        }
    }

    @Operation(summary = "Lista todas as aliquotas", description = "Retorna uma lista de todas as aliquotas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de aliquotas retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Aliquota>> listarTodasAliquotas() {
        List<Aliquota> aliquotas = aliquotaService.listarTodas();
        return ResponseEntity.ok(aliquotas);
    }

    @Operation(summary = "Busca aliquota por ID", description = "Retorna a aliquota correspondente ao ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Aliquota encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Aliquota não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Aliquota> buscarAliquotaPorId(@PathVariable Long id) {
        try {
            Aliquota aliquota = aliquotaService.buscarPorId(id);
            return ResponseEntity.ok(aliquota);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Busca aliquota por categoria", description = "Retorna a aliquota correspondente à categoria fornecida.")
    @ApiResponse(responseCode = "200", description = "Aliquota encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Aliquota não encontrada")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Aliquota> buscarAliquotaPorCategoria(@PathVariable String categoria) {
        Aliquota aliquota = aliquotaService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(aliquota);
    }

    @Operation(summary = "Atualiza uma aliquota", description = "Atualiza a aliquota com a categoria fornecida com as novas informações.")
    @ApiResponse(responseCode = "200", description = "Aliquota atualizada com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Aliquota> atualizarAliquotaPorId(@PathVariable Long id, @RequestBody AliquotaDTO aliquotaDTO) {
        try {
            Aliquota aliquotaAtualizada = aliquotaService.atualizarAliquota(id, aliquotaDTO);
            return ResponseEntity.ok(aliquotaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Deleta uma aliquota", description = "Deleta a aliquota correspondente ao ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Aliquota deletada com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAliquotaPorId(@PathVariable Long id) {
        try {
            aliquotaService.deletarAliquota(id);
            return ResponseEntity.ok("Aliquota deletada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar alíquota.");
        }
    }

    @Operation(summary = "Deleta uma aliquota por categoria", description = "Deleta a aliquota correspondente à categoria fornecida.")
    @ApiResponse(responseCode = "200", description = "Aliquota deletada com sucesso")
    @DeleteMapping("/categoria/{categoria}")
    public ResponseEntity<?> deletarAliquotaPorCategoria(@PathVariable String categoria) {
        try {
            aliquotaService.deletarAliquotaPorCategoria(categoria);
            return ResponseEntity.ok("Aliquota deletada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar alíquota por categoria.");
        }
    }
}

package com.previdencia.gestaocontribuicao.controller;
import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import com.previdencia.gestaocontribuicao.service.AliquotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Aliquota> criarAliquota(@RequestBody AliquotaDTO aliquotaDTO) {
        Aliquota novaAliquota = aliquotaService.criarAliquota(aliquotaDTO);
        return ResponseEntity.ok(novaAliquota);
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
        Aliquota aliquota = aliquotaService.buscarPorId(id);
        return ResponseEntity.ok(aliquota);
    }
    @Operation(summary = "Atualiza uma aliquota", description = "Atualiza a aliquota com o ID fornecido com as novas informações.")
    @ApiResponse(responseCode = "200", description = "Aliquota atualizada com sucesso")

    @PutMapping("/{id}")
    public ResponseEntity<Aliquota> atualizarAliquota(@PathVariable Long id, @RequestBody AliquotaDTO aliquotaDTO) {
        Aliquota aliquotaAtualizada = aliquotaService.atualizarAliquota(id, aliquotaDTO);
        return ResponseEntity.ok(aliquotaAtualizada);
    }

    @Operation(summary = "Deleta uma aliquota", description = "Deleta a aliquota correspondente ao ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Aliquota deletada com sucesso")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAliquota(@PathVariable Long id) {
        aliquotaService.deletarAliquota(id);
        return ResponseEntity.ok("Aliquota deletada com sucesso");
    }
}

package com.previdencia.gestaocontribuicao.controller;
import com.previdencia.gestaocontribuicao.dto.AliquotaDTO;
import com.previdencia.gestaocontribuicao.model.Aliquota;
import com.previdencia.gestaocontribuicao.service.AliquotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
//Classe resposável por tratar as requisições HTTP relacionadas a aliquotas
@RestController // Anotação que diz ao Spring que esta classe vai ser usada para tratar requisições web.
@RequestMapping("/aliquotas") // Define o caminho para todas as requisições tratadas no controller
public class AliquotaController {

    @Autowired //injeção de dependecia
    private AliquotaService aliquotaService;

    @PostMapping
    public ResponseEntity<Aliquota> criarAliquota(@RequestBody AliquotaDTO aliquotaDTO) {
        Aliquota novaAliquota = aliquotaService.criarAliquota(aliquotaDTO);
        return ResponseEntity.ok(novaAliquota);
    }

    @GetMapping
    public ResponseEntity<List<Aliquota>> listarTodasAliquotas() {
        List<Aliquota> aliquotas = aliquotaService.listarTodas();
        return ResponseEntity.ok(aliquotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aliquota> buscarAliquotaPorId(@PathVariable Long id) {
        Aliquota aliquota = aliquotaService.buscarPorId(id);
        return ResponseEntity.ok(aliquota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aliquota> atualizarAliquota(@PathVariable Long id, @RequestBody AliquotaDTO aliquotaDTO) {
        Aliquota aliquotaAtualizada = aliquotaService.atualizarAliquota(id, aliquotaDTO);
        return ResponseEntity.ok(aliquotaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAliquota(@PathVariable Long id) {
        aliquotaService.deletarAliquota(id);
        return ResponseEntity.ok().build();
    }
}

package com.jei.web.Controller;

import com.jei.applicacion.Service.EpicasService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.web.Dto.EpicasRequestDto;
import com.jei.web.Dto.EpicasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epicas")
@RequiredArgsConstructor
public class EpicasController {

    private final EpicasService epicasService;

    @GetMapping
    public ResponseEntity<List<EpicasResponseDto>> buscar(@RequestParam(value = "departamento", required = false) Departamento departamento,
                                                          @RequestParam(value = "estado", required = false) Estado estado) {
        List<EpicasResponseDto> issues;

        if (departamento != null && estado != null) {
            issues = epicasService.buscarPorDepartamentoYEstado(departamento, estado);
        }
        else {
            issues = epicasService.buscarPorDepartamento(departamento);
        }

        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpicasResponseDto> buscarPorId(@PathVariable Long id) {
        EpicasResponseDto epicas = epicasService.buscarPorId(id);
        return ResponseEntity.ok(epicas);
    }

    @PostMapping
    public ResponseEntity<EpicasResponseDto> crear(@RequestBody EpicasRequestDto epicaRequest) {
        EpicasResponseDto nuevaEpica = epicasService.crear(epicaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEpica);
    }

    // Editar una épica existente
    @PutMapping("/{id}")
    public ResponseEntity<EpicasResponseDto> editar(@PathVariable Long id,
                                                    @RequestBody EpicasRequestDto epicaRequest) {
        EpicasResponseDto epicaEditada = epicasService.editar(id, epicaRequest);
        return ResponseEntity.ok(epicaEditada);
    }
}

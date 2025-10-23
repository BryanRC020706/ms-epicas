package com.jei.web.Controller;

import com.jei.applicacion.Service.EpicasService;
import com.jei.web.Dto.EpicasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/epicas")
@RequiredArgsConstructor
public class EpicasController {

    private final EpicasService epicasService;

    @GetMapping
    public ResponseEntity<List<EpicasResponseDto>> buscar() {
        List<EpicasResponseDto> epicas = epicasService.buscar();
        return ResponseEntity.ok(epicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpicasResponseDto> buscarPorId(@PathVariable Long id) {
        EpicasResponseDto epicas = epicasService.buscarPorId(id);
        return ResponseEntity.ok(epicas);
    }
}

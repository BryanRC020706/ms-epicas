package com.jei.web.Dto;

import com.jei.applicacion.client.ProyectoResponseDto;
import com.jei.applicacion.client.UsuarioResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpicasResponseDto {
    private Long id;
    private String nombre;
    private String estado;
    private ProyectoResponseDto proyecto;
    private String departamento;
    private UsuarioResponseDto usuario;
    private LocalDate fecha;
}

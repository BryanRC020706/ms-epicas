package com.jei.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpicasRequestDto {
    private String nombre;
    private String estado;
    private Long proyecto;
    private String departamento;
    private Long usuario;
    private LocalDate fecha;
}

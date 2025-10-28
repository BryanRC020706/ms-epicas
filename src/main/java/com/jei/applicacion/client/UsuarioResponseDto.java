package com.jei.applicacion.client;

import com.jei.dominio.entidad.Departamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private Role role;
    private Departamento departamento;
}

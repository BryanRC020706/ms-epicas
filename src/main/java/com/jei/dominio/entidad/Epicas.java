package com.jei.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "epicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Epicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String proyecto;
    @Enumerated(EnumType.STRING)
    private Departamento departamento;
    private String usuario;
    private LocalDate fecha;
}

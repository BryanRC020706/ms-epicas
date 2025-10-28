package com.jei.dominio.repository;

import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Epicas;
import com.jei.dominio.entidad.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpicasRepository extends JpaRepository<Epicas, Long> {
    List<Epicas> findByDepartamentoAndEstado(Departamento departamento, Estado estado);
    List<Epicas> findByDepartamento(Departamento departamento);
}

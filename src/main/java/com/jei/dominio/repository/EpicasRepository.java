package com.jei.dominio.repository;

import com.jei.dominio.entidad.Epicas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpicasRepository extends JpaRepository<Epicas, Long> {
}

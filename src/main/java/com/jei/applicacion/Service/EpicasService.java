package com.jei.applicacion.Service;

import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.web.Dto.EpicasResponseDto;

import java.util.List;

public interface EpicasService {
    List<EpicasResponseDto> buscar();
    EpicasResponseDto buscarPorId(Long id);
    List<EpicasResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado);
}

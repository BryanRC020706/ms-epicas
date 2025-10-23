package com.jei.applicacion.Service;

import com.jei.web.Dto.EpicasResponseDto;

import java.util.List;

public interface EpicasService {
        List<EpicasResponseDto> buscar();
    EpicasResponseDto buscarPorId(Long id);
}

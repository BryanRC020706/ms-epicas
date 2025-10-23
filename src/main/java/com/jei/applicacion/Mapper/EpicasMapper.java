package com.jei.applicacion.Mapper;

import com.jei.dominio.entidad.Epicas;
import com.jei.web.Dto.EpicasRequestDto;
import com.jei.web.Dto.EpicasResponseDto;

public interface EpicasMapper {
    Epicas toDomain(EpicasRequestDto epicasRequestDto);
    EpicasResponseDto toDto(Epicas epicas);
}

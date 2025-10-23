package com.jei.applicacion.Mapper.Impl;

import com.jei.applicacion.Mapper.EpicasMapper;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Epicas;
import com.jei.dominio.entidad.Estado;
import com.jei.web.Dto.EpicasRequestDto;
import com.jei.web.Dto.EpicasResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EpicasMapperImpl implements EpicasMapper {

    @Override
    public Epicas toDomain(EpicasRequestDto epicasRequestDto) {
        return Epicas.builder()
                .nombre(epicasRequestDto.getNombre())
                .estado(Estado.valueOf(epicasRequestDto.getEstado()))
                .proyecto(epicasRequestDto.getProyecto())
                .departamento(Departamento.valueOf(epicasRequestDto.getDepartamento()))
                .usuario(epicasRequestDto.getUsuario())
                .fecha(epicasRequestDto.getFecha())
                .build();
    }

    @Override
    public EpicasResponseDto toDto(Epicas epicas) {
        return EpicasResponseDto.builder()
                .id(epicas.getId())
                .nombre(epicas.getNombre())
                .estado(epicas.getEstado().name())
                .proyecto(epicas.getProyecto())
                .departamento(epicas.getDepartamento().name())
                .usuario(epicas.getUsuario())
                .fecha(epicas.getFecha())
                .build();
    }
}

package com.jei.applicacion.Service.Impl;

import com.jei.applicacion.Mapper.EpicasMapper;
import com.jei.applicacion.Service.EpicasService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Epicas;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.repository.EpicasRepository;
import com.jei.web.Dto.EpicasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpicasServiceImpl implements EpicasService {

    private final EpicasRepository epicasRepository;
    private final EpicasMapper epicasMapper;

    @Override
    public List<EpicasResponseDto> buscar() {
        List<Epicas> epicas = epicasRepository.findAll();
        return epicas.stream()
                .map(epicasMapper::toDto)
                .toList();
    }

    @Override
    public EpicasResponseDto buscarPorId(Long id) {
        Epicas epicas = epicasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue no encontrado con ID: " + id));
        return epicasMapper.toDto(epicas);
    }

    @Override
    public List<EpicasResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado) {
        return epicasRepository.findByDepartamentoAndEstado(departamento, estado)
                .stream()
                .map(epicasMapper::toDto)
                .toList();
    }
}

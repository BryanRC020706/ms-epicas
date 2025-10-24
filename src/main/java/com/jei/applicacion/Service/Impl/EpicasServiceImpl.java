package com.jei.applicacion.Service.Impl;

import com.jei.applicacion.Mapper.EpicasMapper;
import com.jei.applicacion.Service.EpicasService;
import com.jei.applicacion.client.ProyectoClient;
import com.jei.applicacion.client.ProyectoResponseDto;
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
    private final ProyectoClient proyectoClient;

    @Override
    public List<EpicasResponseDto> buscar() {
        List<Epicas> epicas = epicasRepository.findAll();
        return epicas.stream()
                .map(epica -> {
                    EpicasResponseDto dto = epicasMapper.toDto(epica);
                    if (epica.getProyecto() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(epica.getProyecto());
                            dto.setProyecto(proyecto);
                        } catch (Exception e) {
                            System.out.println("no se encontro la epica con ID: " + epica.getProyecto());
                        }
                    }
                    return dto;
                })
                .toList();
    }

    @Override
    public EpicasResponseDto buscarPorId(Long id) {
        Epicas epica = epicasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("epica no encontrado con ID: " + id));
        EpicasResponseDto dto = epicasMapper.toDto(epica);

        if (epica.getProyecto() != null) {
            try {
                ProyectoResponseDto proyecto = proyectoClient.buscarPorId(epica.getProyecto());
                dto.setProyecto(proyecto);
            } catch (Exception e) {
                System.out.println("no se encontro el proyecto con ID: " + epica.getProyecto());
            }
        }

        return dto;
    }

    @Override
    public List<EpicasResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado) {
        return epicasRepository.findByDepartamentoAndEstado(departamento, estado)
                .stream()
                .map(epica -> {
                    EpicasResponseDto dto = epicasMapper.toDto(epica);
                    if (epica.getProyecto() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(epica.getProyecto());
                            dto.setProyecto(proyecto);
                        } catch (Exception e) {
                            System.out.println("no se encontro el proyecto con ID: " + epica.getProyecto());
                        }
                    }
                    return dto;
                })
                .toList();
    }
}

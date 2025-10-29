package com.jei.applicacion.Service.Impl;

import com.jei.applicacion.Mapper.EpicasMapper;
import com.jei.applicacion.Service.EpicasService;
import com.jei.applicacion.client.ProyectoClient;
import com.jei.applicacion.client.ProyectoResponseDto;
import com.jei.applicacion.client.UsuarioClient;
import com.jei.applicacion.client.UsuarioResponseDto;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Epicas;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.repository.EpicasRepository;
import com.jei.web.Dto.EpicasRequestDto;
import com.jei.web.Dto.EpicasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EpicasServiceImpl implements EpicasService {

    private final EpicasRepository epicasRepository;
    private final EpicasMapper epicasMapper;
    private final ProyectoClient proyectoClient;
    private final UsuarioClient usuarioClient;

    @Override
    public List<EpicasResponseDto> buscar() {
        List<Epicas> epicas = epicasRepository.findAll();
        return epicas.stream()
                .map(epica -> {
                    EpicasResponseDto dto = epicasMapper.toDto(epica);
                    if (epica.getProyecto() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(epica.getProyecto());
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(epica.getUsuario());
                            dto.setUsuario(usuario);
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
                UsuarioResponseDto usuario = usuarioClient.buscarPorId(epica.getUsuario());
                dto.setUsuario(usuario);
                dto.setProyecto(proyecto);
            } catch (Exception e) {
                System.out.println("no se encontro el proyecto con ID: " + epica.getProyecto());
            }
        }

        return dto;
    }

    @Override
    public List<EpicasResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userRole = "ADMIN";
        String userDepartamento = "COMERCIAL";

        if (auth != null && auth.getPrincipal() != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Jwt jwt) {
                userRole = jwt.getClaimAsString("role");
                userDepartamento = jwt.getClaimAsString("departamento");
            }
        }

        Departamento departamentoFinal = "ADMIN".equalsIgnoreCase(userRole)
                ? departamento
                : Departamento.valueOf(userDepartamento);
        return epicasRepository.findByDepartamentoAndEstado(departamentoFinal, estado)
                .stream()
                .map(epica -> {
                    EpicasResponseDto dto = epicasMapper.toDto(epica);
                    if (epica.getProyecto() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(epica.getProyecto());
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(epica.getUsuario());
                            dto.setUsuario(usuario);
                            dto.setProyecto(proyecto);
                        } catch (Exception e) {
                            System.out.println("no se encontro el proyecto con ID: " + epica.getProyecto());
                        }
                    }
                    return dto;
                })
                .toList();
    }

    @Override
    public List<EpicasResponseDto> buscarPorDepartamento(Departamento departamento) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userRole = "ADMIN";
        String userDepartamento = "COMERCIAL";

        if (auth != null && auth.getPrincipal() != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Jwt jwt) {
                userRole = jwt.getClaimAsString("role");
                userDepartamento = jwt.getClaimAsString("departamento");
            }
        }

        Departamento departamentoFinal = "ADMIN".equalsIgnoreCase(userRole)
                ? departamento
                : Departamento.valueOf(userDepartamento);

        return epicasRepository.findByDepartamento(departamentoFinal)
                .stream()
                .map(epica -> {
                    EpicasResponseDto dto = epicasMapper.toDto(epica);
                    if (epica.getProyecto() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(epica.getProyecto());
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(epica.getUsuario());
                            dto.setUsuario(usuario);
                            dto.setProyecto(proyecto);
                        } catch (Exception e) {
                            System.out.println("no se encontro el proyecto con ID: " + epica.getProyecto());
                        }
                    }
                    return dto;
                })
                .toList();
    }

    @Override
    public EpicasResponseDto crear(EpicasRequestDto epicaRequest) {
        Epicas epica = epicasMapper.toDomain(epicaRequest);

        Epicas saved = epicasRepository.save(epica);

        EpicasResponseDto dto = epicasMapper.toDto(saved);
        try {
            if (saved.getProyecto() != null) {
                dto.setProyecto(proyectoClient.buscarPorId(saved.getProyecto()));
            }
            if (saved.getUsuario() != null) {
                dto.setUsuario(usuarioClient.buscarPorId(saved.getUsuario()));
            }
        } catch (Exception ignored) {}

        return dto;
    }

    @Override
    public EpicasResponseDto editar(Long id, EpicasRequestDto epicaRequest) {
        Epicas existing = epicasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró épica con ID: " + id));

        existing.setNombre(epicaRequest.getNombre());
        existing.setEstado(Estado.valueOf(epicaRequest.getEstado()));
        existing.setProyecto(epicaRequest.getProyecto());
        existing.setDepartamento(Departamento.valueOf(epicaRequest.getDepartamento()));
        existing.setUsuario(epicaRequest.getUsuario());
        existing.setFecha(epicaRequest.getFecha());

        Epicas updated = epicasRepository.save(existing);

        EpicasResponseDto dto = epicasMapper.toDto(updated);
        try {
            if (updated.getProyecto() != null) {
                dto.setProyecto(proyectoClient.buscarPorId(updated.getProyecto()));
            }
            if (updated.getUsuario() != null) {
                dto.setUsuario(usuarioClient.buscarPorId(updated.getUsuario()));
            }
        } catch (Exception ignored) {}

        return dto;
    }
}

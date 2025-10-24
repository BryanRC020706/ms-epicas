package com.jei.applicacion.client;

import com.jei.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-proyectos",
        url = "http://localhost:8084/api/proyectos",
        configuration = FeignSecurityConfig.class
)
public interface ProyectoClient {
    @GetMapping("/{id}")
    ProyectoResponseDto buscarPorId(@PathVariable("id") Long id);
}   

package com.erickdc.lacasadelalfajorbackend.controller;

import com.erickdc.lacasadelalfajorbackend.dto.ProductoResumenResponse;
import com.erickdc.lacasadelalfajorbackend.service.FavoritoService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping("/{productoId}")
    public void agregar(@PathVariable Long productoId, Authentication authentication) {
        favoritoService.agregar((Long) authentication.getPrincipal(), productoId);
    }

    @DeleteMapping("/{productoId}")
    public void quitar(@PathVariable Long productoId, Authentication authentication) {
        favoritoService.quitar((Long) authentication.getPrincipal(), productoId);
    }

    @GetMapping
    public List<ProductoResumenResponse> listar(Authentication authentication) {
        return favoritoService.listar((Long) authentication.getPrincipal());
    }
}
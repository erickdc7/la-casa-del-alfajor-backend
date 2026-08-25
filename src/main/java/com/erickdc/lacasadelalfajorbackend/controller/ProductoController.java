package com.erickdc.lacasadelalfajorbackend.controller;

import com.erickdc.lacasadelalfajorbackend.dto.ProductoResumenResponse;
import com.erickdc.lacasadelalfajorbackend.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoResumenResponse> listar() {
        return productoService.listarTodos();
    }
}
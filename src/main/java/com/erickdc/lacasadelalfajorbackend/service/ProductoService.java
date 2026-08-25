package com.erickdc.lacasadelalfajorbackend.service;

import com.erickdc.lacasadelalfajorbackend.dto.ProductoResumenResponse;
import com.erickdc.lacasadelalfajorbackend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResumenResponse> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoResumenResponse::from)
                .toList();
    }
}
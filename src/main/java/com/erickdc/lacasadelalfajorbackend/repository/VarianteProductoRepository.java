package com.erickdc.lacasadelalfajorbackend.repository;

import com.erickdc.lacasadelalfajorbackend.entity.VarianteProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VarianteProductoRepository extends JpaRepository<VarianteProducto, Long> {

    List<VarianteProducto> findByProductoIdOrderByOrden(Long productoId);
}
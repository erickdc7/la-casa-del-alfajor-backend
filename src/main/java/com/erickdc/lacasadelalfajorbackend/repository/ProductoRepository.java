package com.erickdc.lacasadelalfajorbackend.repository;

import com.erickdc.lacasadelalfajorbackend.entity.Categoria;
import com.erickdc.lacasadelalfajorbackend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findBySlug(String slug);

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findBySpecialType(String specialType);

    List<Producto> findBySubcategoria(String subcategoria);
}
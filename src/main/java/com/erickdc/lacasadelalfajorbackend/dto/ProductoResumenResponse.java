package com.erickdc.lacasadelalfajorbackend.dto;

import com.erickdc.lacasadelalfajorbackend.entity.BadgeType;
import com.erickdc.lacasadelalfajorbackend.entity.Categoria;
import com.erickdc.lacasadelalfajorbackend.entity.Producto;

import java.math.BigDecimal;

public record ProductoResumenResponse(
        Long id,
        String slug,
        String nombre,
        BigDecimal precio,
        BigDecimal precioOriginal,
        String imagenPrincipal,
        String badge,
        BadgeType badgeType,
        boolean enStock,
        BigDecimal rating,
        Integer cantidadResenas,
        Categoria categoria
) {
    public static ProductoResumenResponse from(Producto producto) {
        return new ProductoResumenResponse(
                producto.getId(),
                producto.getSlug(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getPrecioOriginal(),
                producto.getImagenPrincipal(),
                producto.getBadge(),
                producto.getBadgeType(),
                producto.isEnStock(),
                producto.getRating(),
                producto.getCantidadResenas(),
                producto.getCategoria()
        );
    }
}
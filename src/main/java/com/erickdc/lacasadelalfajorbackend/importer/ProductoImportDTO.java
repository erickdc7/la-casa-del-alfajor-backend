package com.erickdc.lacasadelalfajorbackend.importer;

import java.math.BigDecimal;
import java.util.List;

public record ProductoImportDTO(
        String slug,
        String name,
        BigDecimal price,
        BigDecimal originalPrice,
        String image,
        List<String> images,
        BigDecimal rating,
        Integer reviewsCount,
        String description,
        String ingredients,
        String nutritionalInfo,
        String category,
        String subcategory,
        String heladoType,
        String heladoFlavor,
        String kitType,
        String mixType,
        String specialType,
        Boolean inStock,
        String badge,
        String badgeType,
        String variantType,
        String variantLabel,
        List<VarianteImportDTO> variants
) {
}
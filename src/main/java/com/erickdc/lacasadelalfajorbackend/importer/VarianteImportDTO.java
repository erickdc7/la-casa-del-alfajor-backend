package com.erickdc.lacasadelalfajorbackend.importer;

import java.math.BigDecimal;

public record VarianteImportDTO(
        String label,
        Integer units,
        BigDecimal price
) {
}
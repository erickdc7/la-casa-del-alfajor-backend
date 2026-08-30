package com.erickdc.lacasadelalfajorbackend.importer;

import com.erickdc.lacasadelalfajorbackend.entity.BadgeType;
import com.erickdc.lacasadelalfajorbackend.entity.Categoria;
import com.erickdc.lacasadelalfajorbackend.entity.Producto;
import com.erickdc.lacasadelalfajorbackend.entity.VarianteProducto;
import com.erickdc.lacasadelalfajorbackend.repository.ProductoRepository;
import com.erickdc.lacasadelalfajorbackend.repository.VarianteProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("import")
public class ProductoImporter implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final VarianteProductoRepository varianteProductoRepository;
    private final JsonMapper jsonMapper;

    public ProductoImporter(ProductoRepository productoRepository,
                            VarianteProductoRepository varianteProductoRepository,
                            JsonMapper jsonMapper) {
        this.productoRepository = productoRepository;
        this.varianteProductoRepository = varianteProductoRepository;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productoRepository.count() > 0) {
            System.out.println("La tabla productos ya tiene datos. Importación omitida.");
            return;
        }

        try (InputStream inputStream = new ClassPathResource("data/productos.json").getInputStream()) {
            ProductoImportDTO[] productosImportados = jsonMapper.readValue(inputStream, ProductoImportDTO[].class);

            int exitosos = 0;
            int fallidos = 0;

            for (ProductoImportDTO dto : productosImportados) {
                try {
                    importarProducto(dto);
                    exitosos++;
                } catch (Exception e) {
                    fallidos++;
                    System.out.println("No se pudo importar '" + dto.slug() + "': " + e.getMessage());
                }
            }

            System.out.println("Importación completada: " + exitosos + " productos creados, " + fallidos + " con error.");
        }
    }

    private void importarProducto(ProductoImportDTO dto) {
        Categoria categoria = Categoria.valueOf(dto.category().toUpperCase());

        String imagenPrincipal = normalizarRutaImagen(dto.image());
        List<String> galeria = dto.images().stream()
                .map(this::normalizarRutaImagen)
                .toList();

        BigDecimal precioDesdeVariante = dto.variants().get(0).price();

        Producto producto = new Producto(
                dto.slug(),
                dto.name(),
                dto.description(),
                dto.ingredients(),
                precioDesdeVariante,
                categoria,
                imagenPrincipal
        );

        producto.setPrecioOriginal(dto.originalPrice());
        producto.setInfoNutricional(dto.nutritionalInfo());
        producto.setSubcategoria(dto.subcategory());
        producto.setHeladoTipo(dto.heladoType());
        producto.setHeladoSabor(dto.heladoFlavor());
        producto.setKitTipo(dto.kitType());
        producto.setSpecialType(dto.specialType());
        producto.setBadge(dto.badge());
        producto.setVariantType(dto.variantType());
        producto.setVariantLabel(dto.variantLabel());
        producto.setEnStock(dto.inStock() != null ? dto.inStock() : true);
        producto.setRating(dto.rating());
        producto.setCantidadResenas(dto.reviewsCount());
        producto.setImagenesGaleria(galeria);

        if (dto.badgeType() != null) {
            producto.setBadgeType(BadgeType.valueOf(dto.badgeType().toUpperCase()));
        }

        if (dto.mixType() != null) {
            producto.setMixTipo(Integer.parseInt(dto.mixType()));
        }

        Producto productoGuardado = productoRepository.save(producto);

        int orden = 0;
        for (VarianteImportDTO varianteDto : dto.variants()) {
            VarianteProducto variante = new VarianteProducto(
                    productoGuardado,
                    varianteDto.label(),
                    varianteDto.price()
            );
            variante.setUnidades(varianteDto.units());
            variante.setOrden(orden);
            varianteProductoRepository.save(variante);
            orden++;
        }
    }

    private String normalizarRutaImagen(String ruta) {
        if (ruta.startsWith("../images/")) {
            return ruta.replace("../images/", "/images/");
        }
        return ruta;
    }
}
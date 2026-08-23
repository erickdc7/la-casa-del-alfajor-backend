package com.erickdc.lacasadelalfajorbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String ingredientes;

    @Column(name = "info_nutricional")
    private String infoNutricional;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "precio_original", precision = 10, scale = 2)
    private BigDecimal precioOriginal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Categoria categoria;

    @Column(length = 20)
    private String subcategoria;

    @Column(name = "helado_tipo", length = 20)
    private String heladoTipo;

    @Column(name = "helado_sabor", length = 20)
    private String heladoSabor;

    @Column(name = "kit_tipo", length = 20)
    private String kitTipo;

    @Column(name = "mix_tipo")
    private Integer mixTipo;

    @Column(name = "special_type", length = 30)
    private String specialType;

    @Column(length = 50)
    private String badge;

    @Enumerated(EnumType.STRING)
    @Column(name = "badge_type", length = 20)
    private BadgeType badgeType;

    @Column(name = "variant_type", length = 20)
    private String variantType;

    @Column(name = "variant_label", length = 50)
    private String variantLabel;

    @Column(name = "en_stock", nullable = false)
    private boolean enStock = true;

    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "cantidad_resenas", nullable = false)
    private Integer cantidadResenas = 0;

    @Column(name = "imagen_principal", nullable = false, length = 500)
    private String imagenPrincipal;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "imagenes_galeria", columnDefinition = "text[]", nullable = false)
    private List<String> imagenesGaleria = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "creado_en", nullable = false, updatable = false)
    private OffsetDateTime creadoEn;

    @UpdateTimestamp
    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    protected Producto() {
    }

    public Producto(String slug, String nombre, String descripcion, String ingredientes,
                    BigDecimal precio, Categoria categoria, String imagenPrincipal) {
        this.slug = slug;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.categoria = categoria;
        this.imagenPrincipal = imagenPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getInfoNutricional() {
        return infoNutricional;
    }

    public void setInfoNutricional(String infoNutricional) {
        this.infoNutricional = infoNutricional;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(BigDecimal precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getHeladoTipo() {
        return heladoTipo;
    }

    public void setHeladoTipo(String heladoTipo) {
        this.heladoTipo = heladoTipo;
    }

    public String getHeladoSabor() {
        return heladoSabor;
    }

    public void setHeladoSabor(String heladoSabor) {
        this.heladoSabor = heladoSabor;
    }

    public String getKitTipo() {
        return kitTipo;
    }

    public void setKitTipo(String kitTipo) {
        this.kitTipo = kitTipo;
    }

    public Integer getMixTipo() {
        return mixTipo;
    }

    public void setMixTipo(Integer mixTipo) {
        this.mixTipo = mixTipo;
    }

    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public BadgeType getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(BadgeType badgeType) {
        this.badgeType = badgeType;
    }

    public String getVariantType() {
        return variantType;
    }

    public void setVariantType(String variantType) {
        this.variantType = variantType;
    }

    public String getVariantLabel() {
        return variantLabel;
    }

    public void setVariantLabel(String variantLabel) {
        this.variantLabel = variantLabel;
    }

    public boolean isEnStock() {
        return enStock;
    }

    public void setEnStock(boolean enStock) {
        this.enStock = enStock;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getCantidadResenas() {
        return cantidadResenas;
    }

    public void setCantidadResenas(Integer cantidadResenas) {
        this.cantidadResenas = cantidadResenas;
    }

    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public List<String> getImagenesGaleria() {
        return imagenesGaleria;
    }

    public void setImagenesGaleria(List<String> imagenesGaleria) {
        this.imagenesGaleria = imagenesGaleria;
    }

    public OffsetDateTime getCreadoEn() {
        return creadoEn;
    }

    public OffsetDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
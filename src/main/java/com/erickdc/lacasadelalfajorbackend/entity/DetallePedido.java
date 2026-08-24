package com.erickdc.lacasadelalfajorbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variante_id", nullable = false)
    private VarianteProducto variante;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "etiqueta_variante", nullable = false, length = 100)
    private String etiquetaVariante;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private Integer cantidad;

    protected DetallePedido() {
    }

    public DetallePedido(Pedido pedido, Producto producto, VarianteProducto variante, Integer cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.variante = variante;
        this.nombreProducto = producto.getNombre();
        this.etiquetaVariante = variante.getEtiqueta();
        this.precioUnitario = variante.getPrecio();
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public VarianteProducto getVariante() {
        return variante;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getEtiquetaVariante() {
        return etiquetaVariante;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
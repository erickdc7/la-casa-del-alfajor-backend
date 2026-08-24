package com.erickdc.lacasadelalfajorbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nombre_contacto", nullable = false)
    private String nombreContacto;

    @Column(name = "telefono_contacto", nullable = false, length = 20)
    private String telefonoContacto;

    @Column(name = "email_contacto", nullable = false)
    private String emailContacto;

    @Column(name = "metodo_entrega", nullable = false, length = 20)
    private String metodoEntrega;

    private String direccion;

    @Column(length = 50)
    private String distrito;

    private String referencia;

    @Column(name = "local_recojo", length = 100)
    private String localRecojo;

    @Column(name = "fecha_recojo")
    private LocalDate fechaRecojo;

    @Column(name = "hora_recojo", length = 20)
    private String horaRecojo;

    @Column(name = "es_regalo", nullable = false)
    private boolean esRegalo = false;

    @Column(name = "mensaje_regalo", length = 200)
    private String mensajeRegalo;

    @Column(name = "incluye_empaque_regalo", nullable = false)
    private boolean incluyeEmpaqueRegalo = false;

    @Column(name = "metodo_pago", nullable = false, length = 20)
    private String metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false, length = 20)
    private EstadoPago estadoPago = EstadoPago.PENDIENTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pedido", nullable = false, length = 20)
    private EstadoPedido estadoPedido = EstadoPedido.PENDIENTE;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "costo_envio", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoEnvio;

    @Column(name = "costo_empaque_regalo", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoEmpaqueRegalo = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cupon_id")
    private Cupon cupon;

    @Column(name = "descuento_aplicado", nullable = false, precision = 10, scale = 2)
    private BigDecimal descuentoAplicado = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @CreationTimestamp
    @Column(name = "creado_en", nullable = false, updatable = false)
    private OffsetDateTime creadoEn;

    @UpdateTimestamp
    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    protected Pedido() {
    }

    public Pedido(String nombreContacto, String telefonoContacto, String emailContacto,
                  String metodoEntrega, String metodoPago,
                  BigDecimal subtotal, BigDecimal costoEnvio, BigDecimal total) {
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.emailContacto = emailContacto;
        this.metodoEntrega = metodoEntrega;
        this.metodoPago = metodoPago;
        this.subtotal = subtotal;
        this.costoEnvio = costoEnvio;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String getMetodoEntrega() {
        return metodoEntrega;
    }

    public void setMetodoEntrega(String metodoEntrega) {
        this.metodoEntrega = metodoEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getLocalRecojo() {
        return localRecojo;
    }

    public void setLocalRecojo(String localRecojo) {
        this.localRecojo = localRecojo;
    }

    public LocalDate getFechaRecojo() {
        return fechaRecojo;
    }

    public void setFechaRecojo(LocalDate fechaRecojo) {
        this.fechaRecojo = fechaRecojo;
    }

    public String getHoraRecojo() {
        return horaRecojo;
    }

    public void setHoraRecojo(String horaRecojo) {
        this.horaRecojo = horaRecojo;
    }

    public boolean isEsRegalo() {
        return esRegalo;
    }

    public void setEsRegalo(boolean esRegalo) {
        this.esRegalo = esRegalo;
    }

    public String getMensajeRegalo() {
        return mensajeRegalo;
    }

    public void setMensajeRegalo(String mensajeRegalo) {
        this.mensajeRegalo = mensajeRegalo;
    }

    public boolean isIncluyeEmpaqueRegalo() {
        return incluyeEmpaqueRegalo;
    }

    public void setIncluyeEmpaqueRegalo(boolean incluyeEmpaqueRegalo) {
        this.incluyeEmpaqueRegalo = incluyeEmpaqueRegalo;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(BigDecimal costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public BigDecimal getCostoEmpaqueRegalo() {
        return costoEmpaqueRegalo;
    }

    public void setCostoEmpaqueRegalo(BigDecimal costoEmpaqueRegalo) {
        this.costoEmpaqueRegalo = costoEmpaqueRegalo;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public BigDecimal getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(BigDecimal descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OffsetDateTime getCreadoEn() {
        return creadoEn;
    }

    public OffsetDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
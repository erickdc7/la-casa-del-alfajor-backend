package com.erickdc.lacasadelalfajorbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @Column(nullable = false)
    private String rol = "cliente";

    @Column(name = "terminos_aceptados_en", nullable = false)
    private OffsetDateTime terminosAceptadosEn;

    @Column(name = "acepta_newsletter", nullable = false)
    private boolean aceptaNewsletter;

    @CreationTimestamp
    @Column(name = "creado_en", nullable = false, updatable = false)
    private OffsetDateTime creadoEn;

    @UpdateTimestamp
    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    protected Usuario() {
        // Constructor vacío que exige JPA — Hibernate lo usa internamente.
    }

    public Usuario(String nombre, String email, String telefono, String contrasenaHash,
                   OffsetDateTime terminosAceptadosEn, boolean aceptaNewsletter) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.contrasenaHash = contrasenaHash;
        this.terminosAceptadosEn = terminosAceptadosEn;
        this.aceptaNewsletter = aceptaNewsletter;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public OffsetDateTime getTerminosAceptadosEn() {
        return terminosAceptadosEn;
    }

    public boolean isAceptaNewsletter() {
        return aceptaNewsletter;
    }

    public void setAceptaNewsletter(boolean aceptaNewsletter) {
        this.aceptaNewsletter = aceptaNewsletter;
    }

    public OffsetDateTime getCreadoEn() {
        return creadoEn;
    }

    public OffsetDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
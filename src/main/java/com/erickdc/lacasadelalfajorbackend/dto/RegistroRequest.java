package com.erickdc.lacasadelalfajorbackend.dto;

public record RegistroRequest(
        String nombre,
        String email,
        String telefono,
        String password,
        String confirmPassword,
        boolean aceptaTerminos,
        boolean aceptaNewsletter
) {
}
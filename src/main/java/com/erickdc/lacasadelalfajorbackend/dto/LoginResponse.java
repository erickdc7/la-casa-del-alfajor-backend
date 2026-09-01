package com.erickdc.lacasadelalfajorbackend.dto;

public record LoginResponse(
        String token,
        Long id,
        String nombre,
        String email
) {
}
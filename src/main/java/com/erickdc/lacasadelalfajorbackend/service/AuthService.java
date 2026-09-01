package com.erickdc.lacasadelalfajorbackend.service;

import com.erickdc.lacasadelalfajorbackend.dto.LoginRequest;
import com.erickdc.lacasadelalfajorbackend.dto.LoginResponse;
import com.erickdc.lacasadelalfajorbackend.dto.RegistroRequest;
import com.erickdc.lacasadelalfajorbackend.dto.RegistroResponse;
import com.erickdc.lacasadelalfajorbackend.entity.Usuario;
import com.erickdc.lacasadelalfajorbackend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegistroResponse registrar(RegistroRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        if (!request.aceptaTerminos()) {
            throw new IllegalArgumentException("Debes aceptar los términos y condiciones");
        }
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese email");
        }

        String hash = passwordEncoder.encode(request.password());

        Usuario usuario = new Usuario(
                request.nombre(),
                request.email(),
                request.telefono(),
                hash,
                OffsetDateTime.now(),
                request.aceptaNewsletter()
        );

        Usuario guardado = usuarioRepository.save(usuario);
        return new RegistroResponse(guardado.getId(), guardado.getNombre(), guardado.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Email o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.password(), usuario.getContrasenaHash())) {
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        }

        String token = jwtService.generarToken(usuario, request.rememberMe());
        return new LoginResponse(token, usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
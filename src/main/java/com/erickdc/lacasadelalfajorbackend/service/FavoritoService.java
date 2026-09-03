package com.erickdc.lacasadelalfajorbackend.service;

import com.erickdc.lacasadelalfajorbackend.dto.ProductoResumenResponse;
import com.erickdc.lacasadelalfajorbackend.entity.Favorito;
import com.erickdc.lacasadelalfajorbackend.entity.Producto;
import com.erickdc.lacasadelalfajorbackend.entity.Usuario;
import com.erickdc.lacasadelalfajorbackend.repository.FavoritoRepository;
import com.erickdc.lacasadelalfajorbackend.repository.ProductoRepository;
import com.erickdc.lacasadelalfajorbackend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    public void agregar(Long usuarioId, Long productoId) {
        if (favoritoRepository.existsByUsuarioIdAndProductoId(usuarioId, productoId)) {
            return;
        }
        Usuario usuario = usuarioRepository.getReferenceById(usuarioId);
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        favoritoRepository.save(new Favorito(usuario, producto));
    }

    public void quitar(Long usuarioId, Long productoId) {
        favoritoRepository.deleteByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    public List<ProductoResumenResponse> listar(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId).stream()
                .map(favorito -> ProductoResumenResponse.from(favorito.getProducto()))
                .toList();
    }
}
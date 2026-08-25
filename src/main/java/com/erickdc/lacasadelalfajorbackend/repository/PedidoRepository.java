package com.erickdc.lacasadelalfajorbackend.repository;

import com.erickdc.lacasadelalfajorbackend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioIdOrderByCreadoEnDesc(Long usuarioId);
}
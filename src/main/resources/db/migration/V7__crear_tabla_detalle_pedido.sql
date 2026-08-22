CREATE TABLE detalle_pedido
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pedido_id         BIGINT         NOT NULL,
    producto_id       BIGINT         NOT NULL,
    variante_id       BIGINT         NOT NULL,
    nombre_producto   VARCHAR(255)   NOT NULL,
    etiqueta_variante VARCHAR(100)   NOT NULL,
    precio_unitario   NUMERIC(10, 2) NOT NULL CHECK (precio_unitario >= 0),
    cantidad          INTEGER        NOT NULL CHECK (cantidad > 0),
    CONSTRAINT fk_detalle_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos (id) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_pedido_producto FOREIGN KEY (producto_id) REFERENCES productos (id) ON DELETE RESTRICT,
    CONSTRAINT fk_detalle_pedido_variante FOREIGN KEY (variante_id) REFERENCES variantes_producto (id) ON DELETE RESTRICT
);
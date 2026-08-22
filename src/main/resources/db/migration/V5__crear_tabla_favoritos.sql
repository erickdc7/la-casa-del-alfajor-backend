CREATE TABLE favoritos
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    usuario_id  BIGINT      NOT NULL,
    producto_id BIGINT      NOT NULL,
    creado_en   TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT fk_favorito_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_favorito_producto FOREIGN KEY (producto_id) REFERENCES productos (id) ON DELETE CASCADE,
    CONSTRAINT uq_favorito UNIQUE (usuario_id, producto_id)
);
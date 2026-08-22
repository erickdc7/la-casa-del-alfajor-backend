CREATE TABLE variantes_producto
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    producto_id    BIGINT         NOT NULL,
    etiqueta       VARCHAR(100)   NOT NULL,
    unidades       INTEGER,
    precio         NUMERIC(10, 2) NOT NULL CHECK (precio >= 0),
    orden          SMALLINT       NOT NULL DEFAULT 0,
    creado_en      TIMESTAMPTZ    NOT NULL DEFAULT now(),
    actualizado_en TIMESTAMPTZ    NOT NULL DEFAULT now(),
    CONSTRAINT fk_variante_producto FOREIGN KEY (producto_id) REFERENCES productos (id) ON DELETE CASCADE,
    CONSTRAINT uq_variante_etiqueta UNIQUE (producto_id, etiqueta)
);
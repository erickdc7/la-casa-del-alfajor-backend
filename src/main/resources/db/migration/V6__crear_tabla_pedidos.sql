CREATE TABLE pedidos
(
    id                     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    usuario_id             BIGINT,
    nombre_contacto        VARCHAR(255)   NOT NULL,
    telefono_contacto      VARCHAR(20)    NOT NULL,
    email_contacto         VARCHAR(255)   NOT NULL,
    metodo_entrega         VARCHAR(20)    NOT NULL CHECK (metodo_entrega IN ('delivery', 'pickup')),
    direccion              VARCHAR(255),
    distrito               VARCHAR(50),
    referencia             VARCHAR(255),
    local_recojo           VARCHAR(100),
    fecha_recojo           DATE,
    hora_recojo            VARCHAR(20),
    es_regalo              BOOLEAN        NOT NULL DEFAULT false,
    mensaje_regalo         VARCHAR(200),
    incluye_empaque_regalo BOOLEAN        NOT NULL DEFAULT false,
    metodo_pago            VARCHAR(20)    NOT NULL CHECK (metodo_pago IN ('card', 'yape', 'plin', 'transfer', 'cash')),
    estado_pago            VARCHAR(20)    NOT NULL DEFAULT 'PENDIENTE' CHECK (estado_pago IN ('PENDIENTE', 'CONFIRMADO')),
    estado_pedido          VARCHAR(20)    NOT NULL DEFAULT 'PENDIENTE'
        CHECK (estado_pedido IN ('PENDIENTE', 'CONFIRMADO', 'EN_PREPARACION', 'ENVIADO', 'ENTREGADO', 'CANCELADO')),
    subtotal               NUMERIC(10, 2) NOT NULL CHECK (subtotal >= 0),
    costo_envio            NUMERIC(10, 2) NOT NULL CHECK (costo_envio >= 0),
    costo_empaque_regalo   NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (costo_empaque_regalo >= 0),
    cupon_id               BIGINT,
    descuento_aplicado     NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (descuento_aplicado >= 0),
    total                  NUMERIC(10, 2) NOT NULL CHECK (total >= 0),
    creado_en              TIMESTAMPTZ    NOT NULL DEFAULT now(),
    actualizado_en         TIMESTAMPTZ    NOT NULL DEFAULT now(),
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE SET NULL,
    CONSTRAINT fk_pedido_cupon FOREIGN KEY (cupon_id) REFERENCES cupones (id) ON DELETE SET NULL,
    CONSTRAINT chk_datos_entrega CHECK (
        (metodo_entrega = 'delivery' AND direccion IS NOT NULL AND distrito IS NOT NULL)
            OR
        (metodo_entrega = 'pickup' AND local_recojo IS NOT NULL AND fecha_recojo IS NOT NULL AND
         hora_recojo IS NOT NULL)
        ),
    CONSTRAINT chk_total_correcto CHECK (total = subtotal + costo_envio + costo_empaque_regalo - descuento_aplicado)
);
CREATE TABLE cupones
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo              VARCHAR(50)    NOT NULL UNIQUE,
    tipo_descuento      VARCHAR(20)    NOT NULL CHECK (tipo_descuento IN ('PORCENTAJE', 'MONTO_FIJO')),
    valor               NUMERIC(10, 2) NOT NULL CHECK (valor >= 0),
    monto_minimo_compra NUMERIC(10, 2) CHECK (monto_minimo_compra >= 0),
    fecha_inicio        TIMESTAMPTZ    NOT NULL,
    fecha_fin           TIMESTAMPTZ,
    usos_maximos        INTEGER CHECK (usos_maximos > 0),
    usos_actuales       INTEGER        NOT NULL DEFAULT 0 CHECK (usos_actuales >= 0),
    activo              BOOLEAN        NOT NULL DEFAULT true,
    creado_en           TIMESTAMPTZ    NOT NULL DEFAULT now(),
    actualizado_en      TIMESTAMPTZ    NOT NULL DEFAULT now(),
    CONSTRAINT chk_fecha_fin_posterior CHECK (fecha_fin IS NULL OR fecha_fin > fecha_inicio),
    CONSTRAINT chk_valor_porcentaje_maximo CHECK (tipo_descuento <> 'PORCENTAJE' OR valor <= 100)
);
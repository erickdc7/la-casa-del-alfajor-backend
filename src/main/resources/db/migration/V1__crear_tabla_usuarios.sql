CREATE TABLE usuarios
(
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre                VARCHAR(255) NOT NULL,
    email                 VARCHAR(255) NOT NULL UNIQUE,
    telefono              VARCHAR(20)  NOT NULL,
    contrasena_hash       VARCHAR(60)  NOT NULL,
    rol                   VARCHAR(20)  NOT NULL DEFAULT 'cliente' CHECK (rol IN ('cliente', 'admin')),
    terminos_aceptados_en TIMESTAMPTZ  NOT NULL,
    acepta_newsletter     BOOLEAN      NOT NULL DEFAULT false,
    creado_en             TIMESTAMPTZ  NOT NULL DEFAULT now(),
    actualizado_en        TIMESTAMPTZ  NOT NULL DEFAULT now()
);
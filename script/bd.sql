CREATE TABLE zonas (
                       id_zona INT PRIMARY KEY IDENTITY,
                       nombre VARCHAR(100)
);

CREATE TABLE barrios (
                         id_barrio INT PRIMARY KEY IDENTITY,
                         nombre VARCHAR(100),
                         id_zona INT,
                         FOREIGN KEY (id_zona) REFERENCES zonas(id_zona)
);

CREATE TABLE distribuidores (
                                id_distribuidor INT PRIMARY KEY IDENTITY,
                                tipo_documento VARCHAR(50),
                                nro_documento VARCHAR(50),
                                apellido VARCHAR(100),
                                nombre VARCHAR(100),
                                id_zona INT,
                                FOREIGN KEY (id_zona) REFERENCES zonas(id_zona)
);

CREATE TABLE domicilios (
                            id_domicilio INT PRIMARY KEY IDENTITY,
                            calle VARCHAR(100),
                            depto VARCHAR(50),
                            piso INT,
                            id_barrio INT,
                            FOREIGN KEY (id_barrio) REFERENCES barrios(id_barrio)
);

CREATE TABLE clientes (
                          id_cliente INT PRIMARY KEY IDENTITY,
                          tipo_documento VARCHAR(50),
                          nro_documento VARCHAR(50),
                          apellido VARCHAR(100),
                          nombre VARCHAR(100),
                          razon_social VARCHAR(100),
                          telefono VARCHAR(100),
                          id_domicilio INT,
                          FOREIGN KEY (id_domicilio) REFERENCES domicilios(id_domicilio)
);


CREATE TABLE pedidos (
                         id_pedido INT PRIMARY KEY IDENTITY,
                         fecha_entrega DATE,
                         hora_entrega TIME,
                         observaciones TEXT,
                         cancelado BIT,
                         id_cliente INT,
                         id_zona INT,
                         periodo_venta VARCHAR(1),
                         FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
                         FOREIGN KEY (id_zona) REFERENCES zonas(id_zona)
);

CREATE TABLE productos (
                           id_producto INT PRIMARY KEY IDENTITY,
                           nombre VARCHAR(80),
                           tamano INT,
                           unidad_medida VARCHAR(2),
                           precio DECIMAL(10,2)
);

CREATE TABLE pedidos_detalle (
                                 id_pedidos_detalle INT PRIMARY KEY IDENTITY,
                                 cantidad INT,
                                 precio DECIMAL(10,2),
                                 id_pedido INT,
                                 id_producto INT,
                                 FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
                                 FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);


CREATE TABLE facturas (
                          id_factura INT PRIMARY KEY IDENTITY,
                          numero INT,
                          anulada BIT,
                          id_pedido INT,
                          FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

CREATE TABLE pagos (
                       id_pago INT PRIMARY KEY IDENTITY,
                       importe DECIMAL(10,2),
                       datos_bancarios VARCHAR(255),
                       id_factura INT,
                       medioPagoId VARCHAR(1),
                       FOREIGN KEY (id_factura) REFERENCES facturas(id_factura),
);


-- UPSERTS

CREATE PROCEDURE upsert_cliente (
    @id_cliente INT,
    @tipo_documento VARCHAR(50),
    @nro_documento VARCHAR(50),
    @apellido VARCHAR(100),
    @nombre VARCHAR(100),
    @razon_social VARCHAR(100),
    @telefono VARCHAR(100),
    @id_domicilio INT,
    @calle VARCHAR(100),
    @depto VARCHAR(50),
    @piso INT,
    @id_barrio INT
)
    AS
BEGIN
    IF EXISTS (SELECT 1 FROM domicilios WHERE id_domicilio = @id_domicilio)
BEGIN
UPDATE domicilios
SET calle = @calle,
    depto = @depto,
    piso = @piso,
    id_barrio = @id_barrio
WHERE id_domicilio = @id_domicilio;
END
ELSE
BEGIN
INSERT INTO domicilios (calle, depto, piso, id_barrio)
VALUES (@calle, @depto, @piso, @id_barrio);
SET @id_domicilio = SCOPE_IDENTITY();
END
    IF EXISTS (SELECT 1 FROM clientes WHERE id_cliente = @id_cliente)
BEGIN
UPDATE clientes
SET tipo_documento = @tipo_documento,
    nro_documento = @nro_documento,
    apellido = @apellido,
    nombre = @nombre,
    razon_social = @razon_social,
    telefono = @telefono,
    id_domicilio = @id_domicilio
WHERE id_cliente = @id_cliente;
END
ELSE
BEGIN
INSERT INTO clientes (tipo_documento, nro_documento, apellido, nombre, razon_social, telefono, id_domicilio)
VALUES (@tipo_documento, @nro_documento, @apellido, @nombre, @razon_social, @telefono, @id_domicilio);
END
END;

CREATE PROCEDURE upsert_distribuidor (
    @id_distribuidor INT,
    @tipo_documento VARCHAR(50),
    @nro_documento VARCHAR(50),
    @apellido VARCHAR(100),
    @nombre VARCHAR(100),
    @id_zona INT
)
    AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM zonas WHERE id_zona = @id_zona)
BEGIN
        THROW 50001, 'La zona especificada no existe.', 1;
END;

    IF EXISTS (SELECT 1 FROM distribuidores WHERE id_distribuidor = @id_distribuidor)
BEGIN
UPDATE distribuidores
SET tipo_documento = @tipo_documento,
    nro_documento = @nro_documento,
    apellido = @apellido,
    nombre = @nombre,
    id_zona = @id_zona
WHERE id_distribuidor = @id_distribuidor;
END
ELSE
BEGIN
INSERT INTO distribuidores (tipo_documento, nro_documento, apellido, nombre, id_zona)
VALUES (@tipo_documento, @nro_documento, @apellido, @nombre, @id_zona);

SET @id_distribuidor = SCOPE_IDENTITY();
END
END;

CREATE PROCEDURE upsert_producto (
    @id_producto INT,
    @nombre VARCHAR(20),
    @tamano INT,
    @unidad_medida VARCHAR(2),
    @precio DECIMAL(10, 2)
)
    AS
BEGIN
    IF EXISTS (SELECT 1 FROM productos WHERE id_producto = @id_producto)
BEGIN
UPDATE productos
SET nombre = @nombre,
    tamano = @tamano,
    unidad_medida = @unidad_medida,
    precio = @precio
WHERE id_producto = @id_producto;
END
ELSE
BEGIN
INSERT INTO productos (nombre, tamano, unidad_medida, precio)
VALUES (@nombre, @tamano, @unidad_medida, @precio);

SET @id_producto = SCOPE_IDENTITY();
END
END;


CREATE PROCEDURE upsert_pedido (
    @id_pedido INT,
    @fecha_entrega DATE = NULL,
    @hora_entrega TIME = NULL,
    @observaciones TEXT = NULL,
    @cancelado BIT,
    @id_cliente INT,
    @id_zona INT,
    @periodo_venta VARCHAR(1)
)
    AS
BEGIN
    IF EXISTS (SELECT 1 FROM pedidos WHERE id_pedido = @id_pedido)
BEGIN
UPDATE pedidos
SET fecha_entrega = CASE WHEN @fecha_entrega IS NOT NULL THEN @fecha_entrega ELSE fecha_entrega END,
    hora_entrega = CASE WHEN @hora_entrega IS NOT NULL THEN @hora_entrega ELSE hora_entrega END,
    observaciones = @observaciones,
    cancelado = @cancelado,
    id_cliente = @id_cliente,
    id_zona = @id_zona,
    periodo_venta = @periodo_venta
WHERE id_pedido = @id_pedido;
END
ELSE
BEGIN
INSERT INTO pedidos (fecha_entrega, hora_entrega, observaciones, cancelado, id_cliente, id_zona, periodo_venta)
VALUES (
           @fecha_entrega,
           @hora_entrega,
           @observaciones,
           @cancelado,
           @id_cliente,
           @id_zona,
           @periodo_venta
       );

SET @id_pedido = SCOPE_IDENTITY();
END
END;

CREATE PROCEDURE upsert_pedido_detalle(
    @id_pedidos_detalle INT,
    @cantidad INT,
    @precio DECIMAL(10, 2),
    @id_pedido INT,
    @id_producto INT
)
    AS
BEGIN
    IF @id_pedidos_detalle IS NULL OR @id_pedidos_detalle = 0
BEGIN
INSERT INTO pedidos_detalle (cantidad, precio, id_pedido, id_producto)
VALUES (@cantidad, @precio, @id_pedido, @id_producto);
END
ELSE
BEGIN
UPDATE pedidos_detalle
SET cantidad = @cantidad, precio = @precio, id_producto = @id_producto
WHERE id_pedidos_detalle = @id_pedidos_detalle;
END
END;



-- SP DE CONSULTAS

CREATE PROCEDURE buscar_clientes (
    @filtroBusqueda VARCHAR(255)
)
    AS
BEGIN
SELECT *
FROM clientes
WHERE
    (tipo_documento LIKE '%' + @filtroBusqueda + '%') OR
    (nro_documento LIKE '%' + @filtroBusqueda + '%') OR
    (apellido LIKE '%' + @filtroBusqueda + '%') OR
    (nombre LIKE '%' + @filtroBusqueda + '%') OR
    (razon_social LIKE '%' + @filtroBusqueda + '%') OR
    (telefono LIKE '%' + @filtroBusqueda + '%')
END

CREATE PROCEDURE buscar_distribuidores (
    @filtroBusqueda VARCHAR(255)
)
    AS
BEGIN
SELECT
    d.id_distribuidor,
    d.tipo_documento,
    d.nro_documento,
    d.apellido,
    d.nombre,
    z.nombre AS nombre_zona
FROM
    distribuidores d
        LEFT JOIN
    zonas z ON d.id_zona = z.id_zona
WHERE
    (d.tipo_documento LIKE '%' + @filtroBusqueda + '%') OR
    (d.nro_documento LIKE '%' + @filtroBusqueda + '%') OR
    (d.apellido LIKE '%' + @filtroBusqueda + '%') OR
    (d.nombre LIKE '%' + @filtroBusqueda + '%') OR
    (z.nombre LIKE '%' + @filtroBusqueda + '%');
END;

CREATE PROCEDURE buscar_productos (
    @filtroBusqueda VARCHAR(255)
)
    AS
BEGIN
SELECT
    p.id_producto,
    p.nombre,
    p.tamano,
    p.unidad_medida,
    p.precio
FROM
    productos p
WHERE
    (p.nombre LIKE '%' + @filtroBusqueda + '%') OR
    (CAST(p.tamano AS VARCHAR) LIKE '%' + @filtroBusqueda + '%') OR
    (p.unidad_medida LIKE '%' + @filtroBusqueda + '%');
END;

CREATE PROCEDURE buscar_pedidos (
    @filtroBusqueda VARCHAR(255)
)
    AS
BEGIN
SELECT
    p.id_pedido,
    p.fecha_entrega,
    p.hora_entrega,
    p.observaciones,
    p.cancelado,
    p.id_cliente,
    p.id_zona,
    p.periodo_venta
FROM
    pedidos p
        LEFT JOIN
    clientes c ON p.id_cliente = c.id_cliente
        LEFT JOIN
    zonas z ON p.id_zona = z.id_zona
WHERE
    (p.fecha_entrega LIKE '%' + @filtroBusqueda + '%') OR
    (p.hora_entrega LIKE '%' + @filtroBusqueda + '%') OR
    (p.observaciones LIKE '%' + @filtroBusqueda + '%') OR
    (c.nombre LIKE '%' + @filtroBusqueda + '%') OR
    (z.nombre LIKE '%' + @filtroBusqueda + '%') OR
    (p.periodo_venta LIKE '%' + @filtroBusqueda + '%');
END;

CREATE PROCEDURE get_barrio_domicilio
    @domicilio_id INT
AS
BEGIN
SELECT
    b.id_barrio,
    b.nombre AS nombre_barrio
FROM
    barrios b
        INNER JOIN
    domicilios d ON b.id_barrio = d.id_barrio
WHERE
    d.id_domicilio = @domicilio_id;
END

CREATE PROCEDURE get_domicilio_cliente
    @id_cliente INT
AS
BEGIN
SELECT d.id_domicilio, d.calle, d.depto, d.piso, d.id_barrio
FROM domicilios d
         INNER JOIN clientes c ON c.id_domicilio = d.id_domicilio
WHERE c.id_cliente = @id_cliente;
END;

CREATE PROCEDURE get_zona_distribuidor
    @id_distribuidor INT
AS
BEGIN
SELECT
    z.id_zona,
    z.nombre AS nombre_zona
FROM
    zonas z
        INNER JOIN
    distribuidores d ON z.id_zona = d.id_zona
WHERE
    d.id_distribuidor = @id_distribuidor;
END;

CREATE PROCEDURE get_cliente_pedido
    @id_pedido INT
AS
BEGIN
SELECT c.id_cliente, c.tipo_documento, c.nro_documento, c.apellido, c.nombre, c.razon_social, c.telefono
FROM clientes c
         INNER JOIN pedidos p ON c.id_cliente = p.id_cliente
WHERE p.id_pedido = @id_pedido;
END;

CREATE PROCEDURE get_detalle_pedido
    @id_pedido INT
AS
BEGIN
SELECT
    pd.id_detalle_pedido,
    pd.cantidad,
    pd.precio,
    pd.id_pedido,
    pd.id_producto,
    p.nombre AS nombre_producto
FROM
    pedido_detalle pd
        INNER JOIN
    productos p ON pd.id_producto = p.id_producto
WHERE
    pd.id_pedido = @id_pedido;
END;

CREATE PROCEDURE get_productos_pedido_detalles
    @id_pedido INT
AS
BEGIN
SELECT
    p.id_producto,
    p.nombre AS nombre_producto,
    p.tamano,
    p.unidad_medida,
    p.precio
FROM
    pedido_detalle pd
        INNER JOIN
    productos p ON pd.id_producto = p.id_producto
WHERE
    pd.id_pedido = @id_pedido;
END;

CREATE PROCEDURE get_zona_pedido_detalles
    @id_pedido INT
AS
BEGIN
SELECT
    z.id_zona,
    z.nombre AS nombre_zona
FROM
    pedidos p
        INNER JOIN
    zonas z ON p.id_zona = z.id_zona
WHERE
    p.id_pedido = @id_pedido;
END;



-- Insertar Zonas
INSERT INTO zonas(nombre)
VALUES
    ('Centro'),
    ('Norte'),
    ('Sur'),
    ('Este'),
    ('Oeste');

-- Insertar Barrios relacionados con las Zonas
-- Zona: Centro
DECLARE @zonaCentro INT = (SELECT id_zona FROM zonas WHERE nombre = 'Centro');
INSERT INTO barrios (nombre, id_zona)
VALUES
    ('Centro Histórico', @zonaCentro),
    ('Nueva Córdoba', @zonaCentro),
    ('Alta Córdoba', @zonaCentro),
    ('Cerro de las Rosas', @zonaCentro);

-- Zona: Norte
DECLARE @zonaNorte INT = (SELECT id_zona FROM zonas WHERE nombre = 'Norte');
INSERT INTO barrios (nombre, id_zona)
VALUES
    ('Jardín', @zonaNorte),
    ('Marqués de Sobremonte', @zonaNorte),
    ('Villa Allende Parque', @zonaNorte),
    ('Barrio Parque', @zonaNorte);

-- Zona: Sur
DECLARE @zonaSur INT = (SELECT id_zona FROM zonas WHERE nombre = 'Sur');
INSERT INTO barrios (nombre, id_zona)
VALUES
    ('Villa Cabrera', @zonaSur),
    ('Villa El Libertador', @zonaSur),
    ('Margarita', @zonaSur),
    ('Los Boulevares', @zonaSur);

-- Zona: Este
DECLARE @zonaEste INT = (SELECT id_zona FROM zonas WHERE nombre = 'Este');
INSERT INTO barrios (nombre, id_zona)
VALUES
    ('Bialet Massé', @zonaEste),
    ('Río Ceballos', @zonaEste),
    ('Salsipuedes', @zonaEste),
    ('Tanti', @zonaEste);

-- Zona: Oeste
DECLARE @zonaOeste INT = (SELECT id_zona FROM zonas WHERE nombre = 'Oeste');
INSERT INTO barrios (nombre, id_zona)
VALUES
    ('Colón', @zonaOeste),
    ('San Vicente', @zonaOeste),
    ('El Chingolo', @zonaOeste);

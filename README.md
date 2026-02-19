# DistribuidoraGas

Aplicación de escritorio en **JavaFX + Maven + SQL Server** para gestionar una distribuidora de gas con foco en:

- Clientes
- Distribuidores
- Productos
- Pedidos

El sistema está organizado en capas (`controller`, `model`, `dao`, `bo`, `dto`) y utiliza procedimientos almacenados para la persistencia.

## 1) Objetivo funcional

El sistema permite que un operador administrativo gestione datos maestros y pedidos:

- ABM de clientes.
- ABM de distribuidores.
- ABM de productos.
- Registro/edición de pedidos con detalle de productos.

Esto está alineado con los casos de uso modelados en `docs/UseCases.puml`.

## 2) Stack tecnológico

- **Java 17**
- **JavaFX 17** (`javafx-controls`, `javafx-fxml`)
- **Maven**
- **SQL Server** + driver JDBC de Microsoft (`mssql-jdbc`)
- **JUnit 5** + **Mockito** para pruebas unitarias

Dependencias y versión de compilación configuradas en `pom.xml`.

## 3) Arquitectura general

La arquitectura sigue una separación por responsabilidades:

- **`bo/` (Business Objects):** entidades de dominio (`Cliente`, `Pedido`, `Producto`, etc.) y enums/estados.
- **`dto/` (Data Transfer Objects):** adaptación hacia JavaFX (`StringProperty`) para tablas y formularios.
- **`controller/`:** controladores JavaFX que atienden eventos de UI y coordinan casos de uso.
- **`model/`:** capa intermedia de aplicación; compone objetos y delega en DAO.
- **`dao/`:** acceso a base de datos mediante JDBC y SPs.
- **`resources/*.fxml`:** pantallas y formularios.

Flujo típico:

1. Usuario interactúa en una pantalla FXML.
2. `controller` valida campos y construye entidades BO.
3. `model` completa relaciones (por ejemplo, domicilio/barrio/zona).
4. `dao` ejecuta búsquedas/guardados en SQL Server.
5. Resultados se exponen como DTO para las tablas.

## 4) Módulos funcionales

### 4.1 Menú principal

<img width="238" height="430" alt="image" src="https://github.com/user-attachments/assets/689fdf90-d292-4e0b-8320-138aa35ac23f" />

`Main` levanta `menu-view.fxml` y `MenuView` abre las ventanas de gestión:

- Clientes (`clientes-view.fxml`)
- Distribuidores (`distribuidores-view.fxml`)
- Productos (`productos-view.fxml`)
- Pedidos (`pedidos-view.fxml`)


### 4.2 Gestión de clientes

<img width="881" height="493" alt="image" src="https://github.com/user-attachments/assets/cffe27cd-037d-45fc-9c88-a56b94a0f9a3" />

- Vista: `ClienteView`
- Formulario: `ClienteController`
- Modelo: `ClienteModel`, `DomicilioModel`, `BarrioModel`
- DAO: `ClienteDAO`, `DomicilioDAO`, `BarrioDAO`

Incluye selección de tipo de documento y barrio, edición de cliente existente y guardado en SP `upsert_cliente`.

### 4.3 Gestión de distribuidores

- Vista: `DistribuidorView`
- Formulario: `DistribuidorController`
- Modelo: `DistribuidorModel`, `ZonaModel`
- DAO: `DistribuidorDAO`, `ZonaDAO`

Permite asociar distribuidor a una zona y persistir con `upsert_distribuidor`.

### 4.4 Gestión de productos

- Vista: `ProductoView`
- Formulario: `ProductoController`
- Modelo: `ProductosModel`
- DAO: `ProductoDAO`

Soporta búsqueda y alta/modificación de producto con `upsert_producto`.

### 4.5 Gestión de pedidos

- Vista: `PedidoView`
- Formulario: `PedidoController`
- Modelo: `PedidoModel`, `PedidoDetalleModel`, `ClienteModel`, `ZonaModel`
- DAO: `PedidoDAO`, `PedidoDetalleDAO`

Administra cabecera + detalle de pedido, incluyendo período de venta y estado de cancelación.

## 5) Modelo de dominio y patrones

### 5.1 Entidades principales

- `Persona` (abstracta) como base de `Cliente` y `Distribuidor`.
- `Pedido` con `Cliente`, `Zona` y lista de `PedidoDetalle`.
- `PedidoDetalle` asociado a `Producto`.
- `Factura` y `Pago` definidos en dominio (base para expansión futura).

### 5.2 Enums relevantes

- `TipoDocumento`
- `PeriodoVenta`
- `MedioPago`

### 5.3 Patrones presentes

- **Factory Method/Factory simple:** `PersonaFactory`, `ClienteFactory`, `DistribuidorFactory`.
- **State (pedido):** interfaz `EstadoPedido` y estados concretos (`Cotizado`, `Confirmado`, `Despachado`, `Entregado`, `Cancelado`).

## 6) Base de datos

El script `script/bd.sql` define:

- Tablas: `zonas`, `barrios`, `distribuidores`, `domicilios`, `clientes`, `pedidos`, `productos`, `pedidos_detalle`, `facturas`, `pagos`.
- Procedimientos `upsert_*` para persistencia.
- Procedimientos `buscar_*` y `get_*` para consultas específicas.
- Datos semilla de zonas y barrios.

> Nota: el código Java se conecta a SQL Server local con credenciales fijas en `DatabaseConnection`.

## 7) Configuración y ejecución local

### 7.1 Requisitos

- JDK 17
- Maven 3.9+ (o wrapper `./mvnw`)
- SQL Server accesible en `localhost:54106`
- Base de datos `distribuidoragas`

### 7.2 Levantar base

1. Crear la DB `distribuidoragas` en SQL Server.
2. Ejecutar `script/bd.sql`.
3. Verificar usuario/clave configurados en `DatabaseConnection`.

### 7.3 Ejecutar aplicación

```bash
./mvnw clean javafx:run
```

## 8) Pruebas

Pruebas actuales incluidas:

- `ClienteFactoryTest` (creación de cliente vía fábrica).
- `DatabaseConnectionTest` (validación básica de objeto conexión mockeado).

Ejecución:

```bash
./mvnw test
```

## 9) Observaciones técnicas

- La conexión a BD está hardcodeada (URL/usuario/password). Recomendado mover a variables de entorno o archivo de configuración.
- Algunos SP del script SQL muestran pequeñas inconsistencias de naming (`pedido_detalle` vs `pedidos_detalle`) que conviene unificar para evitar errores al desplegar.
- El título de ventana inicial en `Main` está como "Hello!" y puede adaptarse al nombre del sistema.

## 10) Estructura de carpetas

```text
src/main/java/ar/edu/ubp/doo/distribuidoragas/
├── bo/           # Dominio
├── controller/   # JavaFX controllers
├── dao/          # JDBC + SP
├── dto/          # Adaptadores para UI
├── model/        # Capa de aplicación
└── Main.java

src/main/resources/ar/edu/ubp/doo/distribuidoragas/
└── *.fxml        # Vistas

script/
└── bd.sql        # Esquema + SP + datos iniciales

docs/
└── *.puml        # Diagramas UML
```

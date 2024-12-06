package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final String UPSERT_PRODUCTO = "{call upsert_producto(?, ?, ?, ?, ?)}";
    private static final String SQL_BUSCAR_PRODUCTO_POR_FILTRO = "{CALL buscar_productos(?)}";
    private static final String GET_PRODUCTO_DETALLE_PEDIDO_QUERY = "{CALL get_productos_pedido_detalles(?)}";


    public boolean guardarProducto(Producto producto) {
        boolean registroExitoso = false;
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {

            stmt = connection.prepareCall(UPSERT_PRODUCTO);

            stmt.setInt(1, producto.getIdProducto());
            stmt.setString(2, producto.getNombre());
            stmt.setInt(3, producto.getTamano());
            stmt.setString(4, producto.getUnidadMedida());
            stmt.setDouble(5, producto.getPrecio());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                registroExitoso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return registroExitoso;
    }

    public List<Producto> buscarProductos(String criterio) {
        List<Producto> productos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SQL_BUSCAR_PRODUCTO_POR_FILTRO)) {

            callableStatement.setString(1, criterio);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(resultSet.getInt("id_producto"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setTamano(resultSet.getInt("tamano"));
                    producto.setUnidadMedida(resultSet.getString("unidad_medida"));
                    producto.setPrecio(resultSet.getDouble("precio"));
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public Producto obtenerProductoPorDetallePedido(int detallePedidoId) {
        Producto producto = new Producto();;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTO_DETALLE_PEDIDO_QUERY)) {

            preparedStatement.setInt(1, detallePedidoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    producto.setIdProducto(resultSet.getInt("id_producto"));
                    producto.setNombre(resultSet.getString("nombre_producto"));
                    producto.setTamano(resultSet.getInt("tamano"));
                    producto.setUnidadMedida(resultSet.getString("unidad_medida"));
                    producto.setPrecio(resultSet.getDouble("precio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

}

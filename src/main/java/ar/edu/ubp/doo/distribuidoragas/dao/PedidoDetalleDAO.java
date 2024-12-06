package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.PedidoDetalle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDetalleDAO {

    private static final String UPSERT_PEDIDO_DETALLE = "{call upsert_pedido_detalle(?, ?, ?, ?, ?)}";
    private static final String GET_DETALLE_PEDIDO_QUERY = "{CALL get_detalle_pedido(?)}";

    public boolean guardarPedidoDetalle(PedidoDetalle pedidoDetalle, int idPedido) {
        boolean registroExitoso = false;
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {

            stmt = connection.prepareCall(UPSERT_PEDIDO_DETALLE);

            stmt.setInt(1, pedidoDetalle.getIdDetallePedido());
            stmt.setInt(2, pedidoDetalle.getCantidad());
            stmt.setDouble(3, pedidoDetalle.getPrecio());
            stmt.setInt(4, idPedido);
            stmt.setInt(5, pedidoDetalle.getProducto().getIdProducto());

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

    public List<PedidoDetalle> obtenerDetallePorPedido(int idPedido) {
        List<PedidoDetalle> detallesPedido = new ArrayList<>();
        CallableStatement stmt = null;
        ResultSet resultSet = null;

        try (Connection connection = DatabaseConnection.getConnection()) {

            stmt = connection.prepareCall(GET_DETALLE_PEDIDO_QUERY);
            stmt.setInt(1, idPedido);

            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                PedidoDetalle pedidoDetalle = new PedidoDetalle();
                pedidoDetalle.setIdDetallePedido(resultSet.getInt("id_detalle_pedido"));
                pedidoDetalle.setCantidad(resultSet.getInt("cantidad"));
                pedidoDetalle.setPrecio(resultSet.getDouble("precio"));

                detallesPedido.add(pedidoDetalle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return detallesPedido;
    }



}

package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.Pedido;
import ar.edu.ubp.doo.distribuidoragas.bo.PeriodoVenta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private static final String UPSERT_PEDIDO = "{call upsert_pedido(?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String SQL_BUSCAR_PEDIDOS_POR_FILTRO = "{CALL buscar_pedidos(?)}";

    public boolean guardarPedido(Pedido pedido) {
        boolean registroExitoso = false;
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {

            stmt = connection.prepareCall(UPSERT_PEDIDO);

            stmt.setInt(1, pedido.getIdPedido());
            if (pedido.getFechaEntrega() != null) {
                stmt.setString(2, pedido.getFechaEntrega());
            }
            if (pedido.getHoraEntrega() != null) {
                stmt.setString(3, pedido.getHoraEntrega());
            }
            stmt.setString(4, pedido.getObservaciones());
            stmt.setBoolean(5, pedido.isCancelado());
            stmt.setInt(6, pedido.getCliente().getIdCliente());
            stmt.setInt(7, pedido.getZona().getIdZona());
            stmt.setString(8, pedido.getPeriodoVenta().getValue());

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

    public List<Pedido> buscarPedidos(String criterio) {
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SQL_BUSCAR_PEDIDOS_POR_FILTRO)) {

            callableStatement.setString(1, criterio);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setIdPedido(resultSet.getInt("id_pedido"));
                    pedido.setFechaEntrega(resultSet.getString("fecha_entrega"));
                    pedido.setHoraEntrega(resultSet.getString("hora_entrega"));
                    pedido.setObservaciones(resultSet.getString("observaciones"));
                    pedido.setCancelado(resultSet.getBoolean("cancelado"));
                    pedido.setPeriodoVenta(PeriodoVenta.fromValue(resultSet.getString("periodo_venta")));
                    pedidos.add(pedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

}

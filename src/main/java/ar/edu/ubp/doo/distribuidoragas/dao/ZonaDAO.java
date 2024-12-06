package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.Zona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZonaDAO {

    private static final String GET_ZONAS_QUERY = "SELECT * FROM zonas";
    private static final String GET_ZONA_DISTRIBUIDOR_QUERY = "{CALL get_zona_distribuidor(?)}";
    private static final String GET_ZONA_PEDIDO_DETALLE_QUERY = "{CALL get_zona_distribuidor(?)}";


    public List<Zona> obtenerZonas() throws SQLException {
        List<Zona> zonas = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ZONAS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_zona");
                String nombre = resultSet.getString("nombre");
                zonas.add(new Zona(id, nombre));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener zonas", e);
        }
        return zonas;
    }

    public Zona obtenerZonaPorDistribuidor(int distribuidorId) {
        Zona zona = new Zona();
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            stmt = connection.prepareCall(GET_ZONA_DISTRIBUIDOR_QUERY);

            stmt.setInt(1, distribuidorId);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                zona.setIdZona(resultSet.getInt("id_zona"));
                zona.setNombre(resultSet.getString("nombre_zona"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zona;
    }

    public Zona obtenerZonaPorPedidoDetalle(int detallePedidoId) {
        Zona zona = new Zona();
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            stmt = connection.prepareCall(GET_ZONA_PEDIDO_DETALLE_QUERY);

            stmt.setInt(1, detallePedidoId);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                zona.setIdZona(resultSet.getInt("id_zona"));
                zona.setNombre(resultSet.getString("nombre_zona"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zona;
    }

}

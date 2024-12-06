package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.Barrio;
import ar.edu.ubp.doo.distribuidoragas.bo.Zona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarrioDAO {

    private static final String GET_BARRIOS_QUERY = "SELECT * FROM barrios";
    private static final String GET_BARRIOS_DOMICILIO_QUERY = "{CALL get_barrio_domicilio(?)}";


    public List<Barrio> obtenerBarrios() throws SQLException {
        List<Barrio> barrios = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BARRIOS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_barrio");
                String nombre = resultSet.getString("nombre");
                barrios.add(new Barrio(id, nombre));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener barrios", e);
        }
        return barrios;
    }

    public Barrio obtenerBarrioPorDomicilio(int domicilioId) {
        Barrio barrio = new Barrio();
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {
            stmt = connection.prepareCall(GET_BARRIOS_DOMICILIO_QUERY);

            stmt.setInt(1, domicilioId);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                barrio.setIdBarrio(resultSet.getInt("id_barrio"));
                barrio.setNombre(resultSet.getString("nombre_barrio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barrio;
    }

}

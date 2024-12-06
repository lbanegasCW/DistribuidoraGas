package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.Domicilio;

import java.sql.*;

public class DomicilioDAO {

    private static final String GET_DOMICILIO_CLIENTE_QUERY = "{CALL get_domicilio_cliente(?)}";


    public Domicilio obtenerDomicilioPorCliente(int clienteId) {
        Domicilio domicilio = new Domicilio();;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DOMICILIO_CLIENTE_QUERY)) {

            preparedStatement.setInt(1, clienteId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    domicilio.setIdDomicilio(resultSet.getInt("id_domicilio"));
                    domicilio.setCalle(resultSet.getString("calle"));
                    domicilio.setDepto(resultSet.getString("depto"));
                    domicilio.setPiso(resultSet.getInt("piso"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return domicilio;
    }

}

package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.Distribuidor;
import ar.edu.ubp.doo.distribuidoragas.bo.TipoDocumento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistribuidorDAO {

    private static final String UPSERT_DISTRIBUIDOR = "{call upsert_distribuidor(?, ?, ?, ?, ?, ?)}";
    private static final String SQL_BUSCAR_DISTRIBUIDORES_POR_FILTRO = "{CALL buscar_distribuidores(?)}";

    public boolean guardarDistribuidor(Distribuidor distribuidor) {
        boolean registroExitoso = false;
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {

            stmt = connection.prepareCall(UPSERT_DISTRIBUIDOR);

            stmt.setInt(1, distribuidor.getIdDistribuidor());
            stmt.setString(2, distribuidor.getTipoDocumento().getValue());
            stmt.setString(3, distribuidor.getNroDocumento());
            stmt.setString(4, distribuidor.getApellido());
            stmt.setString(5, distribuidor.getNombre());
            stmt.setInt(6, distribuidor.getZona().getIdZona());

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

    public List<Distribuidor> buscarDistribuidores(String criterio) {
        List<Distribuidor> distribuidores = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SQL_BUSCAR_DISTRIBUIDORES_POR_FILTRO)) {

            callableStatement.setString(1, criterio);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    Distribuidor distribuidor = new Distribuidor();
                    distribuidor.setIdDistribuidor(resultSet.getInt("id_distribuidor"));
                    distribuidor.setTipoDocumento(TipoDocumento.fromValue(resultSet.getString("tipo_documento")));
                    distribuidor.setNroDocumento(resultSet.getString("nro_documento"));
                    distribuidor.setApellido(resultSet.getString("apellido"));
                    distribuidor.setNombre(resultSet.getString("nombre"));
                    distribuidores.add(distribuidor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return distribuidores;
    }

}

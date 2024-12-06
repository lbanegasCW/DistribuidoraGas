package ar.edu.ubp.doo.distribuidoragas.dao;

import ar.edu.ubp.doo.distribuidoragas.bo.Cliente;
import ar.edu.ubp.doo.distribuidoragas.bo.Domicilio;
import ar.edu.ubp.doo.distribuidoragas.bo.TipoDocumento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String UPSERT_CLIENTE = "{call upsert_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String SQL_BUSCAR_CLIENTES_POR_FILTRO = "{CALL buscar_clientes(?)}";
    private static final String GET_CLIENTE_PEDIDO_QUERY = "{CALL get_cliente_pedido(?)}";


    public Cliente obtenerClientePorPedido(int pedidoId) {
        Cliente cliente = new Cliente();;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENTE_PEDIDO_QUERY)) {

            preparedStatement.setInt(1, pedidoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente.setIdCliente(resultSet.getInt("id_cliente"));
                    cliente.setTipoDocumento(TipoDocumento.fromValue(resultSet.getString("tipo_documento")));
                    cliente.setNroDocumento(resultSet.getString("nro_documento"));
                    cliente.setApellido(resultSet.getString("apellido"));
                    cliente.setNombre(resultSet.getString("nombre"));
                    cliente.setRazonSocial(resultSet.getString("razon_social"));
                    cliente.setTelefono(resultSet.getLong("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public boolean guardarCliente(Cliente cliente) {
        boolean registroExitoso = false;
        CallableStatement stmt = null;

        try (Connection connection = DatabaseConnection.getConnection()) {

            stmt = connection.prepareCall(UPSERT_CLIENTE);

            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getTipoDocumento().getValue());
            stmt.setString(3, cliente.getNroDocumento());
            stmt.setString(4, cliente.getApellido());
            stmt.setString(5, cliente.getNombre());
            stmt.setString(6, cliente.getRazonSocial());
            stmt.setString(7, Long.toString(cliente.getTelefono()));
            stmt.setInt(8, cliente.getDomicilio().getIdDomicilio());
            stmt.setString(9, cliente.getDomicilio().getCalle());
            stmt.setString(10, cliente.getDomicilio().getDepto());
            stmt.setInt(11, cliente.getDomicilio().getPiso());
            stmt.setInt(12, cliente.getDomicilio().getBarrio().getIdBarrio());

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

    public List<Cliente> buscarClientes(String criterio) {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(SQL_BUSCAR_CLIENTES_POR_FILTRO)) {

            callableStatement.setString(1, criterio);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(resultSet.getInt("id_cliente"));
                    cliente.setTipoDocumento(TipoDocumento.fromValue(resultSet.getString("tipo_documento")));
                    cliente.setNroDocumento(resultSet.getString("nro_documento"));
                    cliente.setApellido(resultSet.getString("apellido"));
                    cliente.setNombre(resultSet.getString("nombre"));
                    cliente.setRazonSocial(resultSet.getString("razon_social"));
                    cliente.setTelefono(resultSet.getLong("telefono"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

}

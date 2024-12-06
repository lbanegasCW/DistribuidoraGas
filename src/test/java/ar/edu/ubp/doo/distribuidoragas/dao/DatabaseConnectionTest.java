package ar.edu.ubp.doo.distribuidoragas.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseConnectionTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private DriverManager mockDriverManager;

    @Test
    public void testConexion() throws SQLException {
        Connection mockConnection = mock(Connection.class);

        try {
            when(mockConnection.isClosed()).thenReturn(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertNotNull(mockConnection);

        try {
            assertFalse(mockConnection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
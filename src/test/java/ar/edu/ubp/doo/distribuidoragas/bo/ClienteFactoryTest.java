package ar.edu.ubp.doo.distribuidoragas.bo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteFactoryTest {

    @Test
    void crearPersona() {
        TipoDocumento tipoDocumento = TipoDocumento.DNI;
        String nroDocumento = "65659874";
        String nombre = "Cosme";
        String apellido = "Fulanito";
        String razonSocial = "12345";
        Long telefono = 0303456L;
        Domicilio domicilio = new Domicilio();

        Cliente cliente = new ClienteFactory().crearPersona(
                tipoDocumento, nroDocumento, nombre, apellido, razonSocial, telefono, domicilio);

        assertNotNull(cliente);
        assertEquals(nombre, cliente.getNombre());
        assertEquals(razonSocial, cliente.getRazonSocial());
    }
}
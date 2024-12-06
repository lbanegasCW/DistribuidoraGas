package ar.edu.ubp.doo.distribuidoragas.bo;

public class ClienteFactory implements PersonaFactory {

    public Cliente crearPersona() {
        return new Cliente();
    }

    public Cliente crearPersona(
            TipoDocumento tipoDocumento,
            String nroDocumento,
            String nombre,
            String apellido,
            String razonSocial,
            long telefono,
            Domicilio domicilio) {

        Cliente cliente = new Cliente();
        cliente.setTipoDocumento(tipoDocumento);
        cliente.setNroDocumento(nroDocumento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setRazonSocial(razonSocial);
        cliente.setTelefono(telefono);
        cliente.setDomicilio(domicilio);

        return cliente;
    }

}

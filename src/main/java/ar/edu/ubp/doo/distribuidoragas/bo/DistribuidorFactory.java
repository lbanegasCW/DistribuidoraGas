package ar.edu.ubp.doo.distribuidoragas.bo;

public class DistribuidorFactory implements PersonaFactory {

    @Override
    public Persona crearPersona() {
        return null;
    }

    public Distribuidor crearPersona(
            TipoDocumento tipoDocumento,
            String nroDocumento,
            String nombre,
            String apellido,
            Zona zona) {

        Distribuidor distribuidor = new Distribuidor();
        distribuidor.setTipoDocumento(tipoDocumento);
        distribuidor.setNroDocumento(nroDocumento);
        distribuidor.setNombre(nombre);
        distribuidor.setApellido(apellido);
        distribuidor.setZona(zona);

        return distribuidor;
    }

}

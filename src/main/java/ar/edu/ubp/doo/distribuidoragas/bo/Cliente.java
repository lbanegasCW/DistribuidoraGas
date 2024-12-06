package ar.edu.ubp.doo.distribuidoragas.bo;

public class Cliente extends Persona {
    private int idCliente;
    private String razonSocial;
    private long telefono;
    private Domicilio domicilio;

    public Cliente() {
        super();
    }

    public Cliente(TipoDocumento tipoDocumento, String nroDocumento, String apellido, String nombre,
                   String razonSocial, long telefono, Domicilio domicilio) {
        super(tipoDocumento, nroDocumento, apellido, nombre);
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return super.getApellido() + " " + super.getNombre() + " - " + razonSocial;
    }

}

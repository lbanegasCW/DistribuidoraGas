package ar.edu.ubp.doo.distribuidoragas.bo;

public abstract class Persona {
    private TipoDocumento tipoDocumento;
    private String nroDocumento;
    private String apellido;
    private String nombre;

    public Persona() {};

    public Persona(TipoDocumento tipoDocumento, String nroDocumento, String apellido, String nombre) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

package ar.edu.ubp.doo.distribuidoragas.bo;

public class Distribuidor extends Persona {
    private int idDistribuidor;
    private Zona zona;

    public Distribuidor() {
        super();
    }

    public Distribuidor(TipoDocumento tipoDocumento, String nroDocumento, String apellido, String nombre, Zona zona) {
        super(tipoDocumento, nroDocumento, apellido, nombre);
        this.zona = zona;
    }

    public int getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(int idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

}



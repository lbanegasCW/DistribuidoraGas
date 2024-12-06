package ar.edu.ubp.doo.distribuidoragas.bo;

public class Domicilio {
    private int idDomicilio;
    private String calle;
    private String depto;
    private int piso;
    private Barrio barrio;

    public Domicilio() {}

    public Domicilio(int idDomicilio, String calle, String depto, int piso, Barrio barrio) {
        this(calle, depto, piso, barrio);
        this.idDomicilio = idDomicilio;
    }

    public Domicilio(String calle, String depto, int piso, Barrio barrio) {
        this.idDomicilio = idDomicilio;
        this.calle = calle;
        this.depto = depto;
        this.piso = piso;
        this.barrio = barrio;
    }

    public int getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(int idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
}

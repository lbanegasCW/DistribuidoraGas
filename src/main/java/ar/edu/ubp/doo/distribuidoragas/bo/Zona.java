package ar.edu.ubp.doo.distribuidoragas.bo;

public class Zona {
    private int idZona;
    private String nombre;
    private Barrio barrio;

    public Zona() {};

    public Zona(int idZona, String nombre) {
        this.idZona = idZona;
        this.nombre = nombre;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    @Override
    public String toString() {
        return nombre;
    }
}



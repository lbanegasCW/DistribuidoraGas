package ar.edu.ubp.doo.distribuidoragas.bo;

public class Barrio {

    private int idBarrio;
    private String nombre;

    public Barrio() {};

    public Barrio(int id, String nombre) {
        this.idBarrio = id;
        this.nombre = nombre;
    }

    public int getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(int idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

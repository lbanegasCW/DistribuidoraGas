package ar.edu.ubp.doo.distribuidoragas.bo;

public class Producto {
    private int idProducto;
    private String nombre;
    private int tamano;
    private String unidadMedida;
    private double precio;

    public Producto() {}

    public Producto(int idProducto, String nombre, int tamano, String unidadMedida, double precio) {
        this(nombre, tamano, unidadMedida, precio);
        this.idProducto = idProducto;
    }

    public Producto(String nombre, int tamano, String unidadMedida, double precio) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return idProducto + " - " + nombre + " - " + tamano + " " + unidadMedida + " - $ " + precio;
    }
}

package ar.edu.ubp.doo.distribuidoragas.dto;

import ar.edu.ubp.doo.distribuidoragas.bo.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductoDTO {
    private StringProperty id;
    private StringProperty nombre;
    private StringProperty tamano;
    private StringProperty unidadMedida;
    private StringProperty precio;

    public ProductoDTO(Producto producto) {
        this.id = new SimpleStringProperty(Integer.toString(producto.getIdProducto()));
        this.nombre = new SimpleStringProperty(producto.getNombre());
        this.tamano = new SimpleStringProperty(Integer.toString(producto.getTamano()));
        this.unidadMedida = new SimpleStringProperty(producto.getUnidadMedida());
        this.precio = new SimpleStringProperty(Double.toString(producto.getPrecio()));
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getTamano() {
        return tamano.get();
    }

    public StringProperty tamanoProperty() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano.set(tamano);
    }

    public String getUnidadMedida() {
        return unidadMedida.get();
    }

    public StringProperty unidadMedidaProperty() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida.set(unidadMedida);
    }

    public String getPrecio() {
        return precio.get();
    }

    public StringProperty precioProperty() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio.set(precio);
    }

}

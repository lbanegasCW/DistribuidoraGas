package ar.edu.ubp.doo.distribuidoragas.dto;

import ar.edu.ubp.doo.distribuidoragas.bo.Distribuidor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DistribuidorDTO {
    private StringProperty id;
    private StringProperty tipoDocumento;
    private StringProperty nroDocumento;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty zona;

    public DistribuidorDTO(Distribuidor distribuidor) {
        this.id = new SimpleStringProperty(Integer.toString(distribuidor.getIdDistribuidor()));
        this.tipoDocumento = new SimpleStringProperty(distribuidor.getTipoDocumento().getValue());
        this.nroDocumento = new SimpleStringProperty(distribuidor.getNroDocumento());
        this.nombre = new SimpleStringProperty(distribuidor.getNombre());
        this.apellido = new SimpleStringProperty(distribuidor.getApellido());
        this.zona = new SimpleStringProperty(distribuidor.getZona().getNombre());
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

    public String getTipoDocumento() {
        return tipoDocumento.get();
    }

    public StringProperty tipoDocumentoProperty() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento.set(tipoDocumento);
    }

    public String getNroDocumento() {
        return nroDocumento.get();
    }

    public StringProperty nroDocumentoProperty() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento.set(nroDocumento);
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

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getZona() {
        return zona.get();
    }

    public StringProperty zonaProperty() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona.set(zona);
    }

}

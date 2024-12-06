package ar.edu.ubp.doo.distribuidoragas.dto;

import ar.edu.ubp.doo.distribuidoragas.bo.Cliente;
import ar.edu.ubp.doo.distribuidoragas.bo.TipoDocumento;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClienteDTO {
    private StringProperty id;
    private StringProperty tipoDocumento;
    private StringProperty nroDocumento;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty razonSocial;
    private StringProperty telefono;
    private StringProperty calle;
    private StringProperty depto;
    private StringProperty piso;
    private StringProperty barrio;

    public ClienteDTO(Cliente cliente) {
        this.id = new SimpleStringProperty(Integer.toString(cliente.getIdCliente()));
        this.tipoDocumento = new SimpleStringProperty(cliente.getTipoDocumento().getValue());
        this.nroDocumento = new SimpleStringProperty(cliente.getNroDocumento());
        this.nombre = new SimpleStringProperty(cliente.getNombre());
        this.apellido = new SimpleStringProperty(cliente.getApellido());
        this.razonSocial = new SimpleStringProperty(cliente.getRazonSocial());
        this.telefono = new SimpleStringProperty(Long.toString(cliente.getTelefono()));
        this.calle = new SimpleStringProperty(cliente.getDomicilio().getCalle());
        this.depto = new SimpleStringProperty(cliente.getDomicilio().getDepto());
        this.piso = new SimpleStringProperty(Integer.toString(cliente.getDomicilio().getPiso()));
        this.barrio = new SimpleStringProperty(cliente.getDomicilio().getBarrio().getNombre());
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

    public String getRazonSocial() {
        return razonSocial.get();
    }

    public StringProperty razonSocialProperty() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial.set(razonSocial);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getCalle() {
        return calle.get();
    }

    public StringProperty calleProperty() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public String getDepto() {
        return depto.get();
    }

    public StringProperty deptoProperty() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto.set(depto);
    }

    public String getPiso() {
        return piso.get();
    }

    public StringProperty pisoProperty() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso.set(piso);
    }

    public String getBarrio() {
        return barrio.get();
    }

    public StringProperty barrioProperty() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio.set(barrio);
    }

}

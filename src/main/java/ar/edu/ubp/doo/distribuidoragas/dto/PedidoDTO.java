package ar.edu.ubp.doo.distribuidoragas.dto;

import ar.edu.ubp.doo.distribuidoragas.bo.Pedido;
import ar.edu.ubp.doo.distribuidoragas.bo.PedidoDetalle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PedidoDTO {
    private StringProperty id;
    private StringProperty nombreCliente;
    private StringProperty apellidoCliente;
    private StringProperty razonSocial;
    private StringProperty calle;
    private StringProperty depto;
    private StringProperty piso;
    private StringProperty productos;
    private StringProperty zona;

    public PedidoDTO(Pedido pedido) {
        this.id = new SimpleStringProperty(Integer.toString(pedido.getIdPedido()));
        this.nombreCliente = new SimpleStringProperty(pedido.getCliente().getNombre());
        this.apellidoCliente = new SimpleStringProperty(pedido.getCliente().getApellido());
        this.razonSocial = new SimpleStringProperty(pedido.getCliente().getRazonSocial());
        this.calle = new SimpleStringProperty(pedido.getCliente().getDomicilio().getCalle());
        this.depto = new SimpleStringProperty(pedido.getCliente().getDomicilio().getDepto());
        this.piso = new SimpleStringProperty(Long.toString(pedido.getCliente().getDomicilio().getPiso()));
        String productos = "";
        for (PedidoDetalle pedidoDetalle: pedido.getPedidoDetalles()) {
            productos.concat(Integer.toString(pedidoDetalle.getCantidad()));
            productos.concat(" x ");
            productos.concat(pedidoDetalle.getProducto().getNombre());
            productos.concat(" | ");
        }
        this.productos = new SimpleStringProperty(productos);
        this.zona = new SimpleStringProperty(pedido.getZona().getNombre());
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

    public String getNombreCliente() {
        return nombreCliente.get();
    }

    public StringProperty nombreClienteProperty() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente.set(nombreCliente);
    }

    public String getApellidoCliente() {
        return apellidoCliente.get();
    }

    public StringProperty apellidoClienteProperty() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente.set(apellidoCliente);
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

    public String getProductos() {
        return productos.get();
    }

    public StringProperty productosProperty() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos.set(productos);
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

package ar.edu.ubp.doo.distribuidoragas.bo;

import java.util.List;

public class Pedido {
    private int idPedido;
    private String fechaEntrega;
    private String horaEntrega;
    private String observaciones;
    private boolean cancelado;
    private Cliente cliente;
    private Zona zona;
    private Factura factura;
    private PeriodoVenta periodoVenta;
    private List<PedidoDetalle> pedidoDetalles;
    private EstadoPedido estado;

    public Pedido() {}

    public Pedido(EstadoPedido estadoInicial) {
        this.estado = estadoInicial;
    }

    public Pedido(int idPedido, String fechaEntrega, String horaEntrega, String observaciones, boolean cancelado,
            Cliente cliente, Zona zona, Factura factura, PeriodoVenta periodoVenta, List<PedidoDetalle> pedidoDetalles) {
        this.idPedido = idPedido;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.observaciones = observaciones;
        this.cancelado = cancelado;
        this.cliente = cliente;
        this.zona = zona;
        this.factura = factura;
        this.periodoVenta = periodoVenta;
        this.pedidoDetalles = pedidoDetalles;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public PeriodoVenta getPeriodoVenta() {
        return periodoVenta;
    }

    public void setPeriodoVenta(PeriodoVenta periodoVenta) {
        this.periodoVenta = periodoVenta;
    }

    public List<PedidoDetalle> getPedidoDetalles() {
        return pedidoDetalles;
    }

    public void setPedidoDetalles(List<PedidoDetalle> pedidoDetalles) {
        this.pedidoDetalles = pedidoDetalles;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public boolean procesar() {
        return estado.procesar(this);
    }

}

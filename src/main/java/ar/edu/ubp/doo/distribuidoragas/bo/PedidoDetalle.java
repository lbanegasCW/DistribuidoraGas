package ar.edu.ubp.doo.distribuidoragas.bo;

public class PedidoDetalle {
    private int idDetallePedido;
    private int cantidad;
    private double precio;
    private Producto producto;

    public PedidoDetalle() {}

    public PedidoDetalle(int idDetallePedido, int cantidad, double precio, Producto producto) {
        this.idDetallePedido = idDetallePedido;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}

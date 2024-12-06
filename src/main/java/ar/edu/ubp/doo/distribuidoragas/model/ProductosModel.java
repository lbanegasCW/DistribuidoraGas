package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.Producto;
import ar.edu.ubp.doo.distribuidoragas.dao.ProductoDAO;

import java.util.List;

public class ProductosModel {
    ProductoDAO productoDAO = new ProductoDAO();

    public boolean guardarProducto(Producto producto) {
        return productoDAO.guardarProducto(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoDAO.buscarProductos("");
    }

    public List<Producto> buscarProductos(String criterio) {
        return productoDAO.buscarProductos(criterio);
    }

    public Producto obtenerProductoPorDetallePedido(int pedidoId) {
        return productoDAO.obtenerProductoPorDetallePedido(pedidoId);
    }

}

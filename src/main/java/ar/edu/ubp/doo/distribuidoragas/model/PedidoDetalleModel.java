package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.PedidoDetalle;
import ar.edu.ubp.doo.distribuidoragas.dao.PedidoDetalleDAO;

import java.util.List;

public class PedidoDetalleModel {
    PedidoDetalleDAO pedidoDetalleDAO = new PedidoDetalleDAO();

    public boolean guardarPedidoDetalle(PedidoDetalle pedidoDetalle, int pedidoId) {
        return pedidoDetalleDAO.guardarPedidoDetalle(pedidoDetalle, pedidoId);
    }

    public List<PedidoDetalle> obtenerDetallePorPedido(int pedidoId) {
        List<PedidoDetalle> pedidoDetalles = pedidoDetalleDAO.obtenerDetallePorPedido(pedidoId);
        for (PedidoDetalle pedidodetalle: pedidoDetalles) {
            pedidodetalle.setProducto(
                    new ProductosModel().obtenerProductoPorDetallePedido(pedidodetalle.getIdDetallePedido()));
        }

        return pedidoDetalles;
    }

}

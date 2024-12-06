package ar.edu.ubp.doo.distribuidoragas.model;

import ar.edu.ubp.doo.distribuidoragas.bo.Pedido;
import ar.edu.ubp.doo.distribuidoragas.dao.PedidoDAO;

import java.util.List;

public class PedidoModel {
    PedidoDAO pedidoDAO = new PedidoDAO();

    public boolean guardarPedido(Pedido pedido) {
        return pedidoDAO.guardarPedido(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = pedidoDAO.buscarPedidos("");
        for (Pedido pedido : pedidos) {
            pedido.setCliente(new ClienteModel().obtenerClientePorPedido(pedido.getIdPedido()));
            pedido.setPedidoDetalles(new PedidoDetalleModel().obtenerDetallePorPedido(pedido.getIdPedido()));
            pedido.setZona(new ZonaModel().obtenerZonaPorPedidoDetalle(pedido.getIdPedido()));
        }
        return pedidos;
    }

    public List<Pedido> buscarPedidos(String criterio) {
        List<Pedido> pedidos = pedidoDAO.buscarPedidos(criterio);
        for (Pedido pedido : pedidos) {
            pedido.setCliente(new ClienteModel().obtenerClientePorPedido(pedido.getIdPedido()));
            pedido.setPedidoDetalles(new PedidoDetalleModel().obtenerDetallePorPedido(pedido.getIdPedido()));
            pedido.setZona(new ZonaModel().obtenerZonaPorPedidoDetalle(pedido.getIdPedido()));
        }
        return pedidos;
    }

}

package ar.edu.ubp.doo.distribuidoragas.bo;

public class EstadoPedidoConfirmado implements EstadoPedido {

    @Override
    public boolean procesar(Pedido pedido) {
        return true;
    }

}

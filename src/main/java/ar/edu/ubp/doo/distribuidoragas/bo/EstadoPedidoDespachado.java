package ar.edu.ubp.doo.distribuidoragas.bo;

public class EstadoPedidoDespachado implements EstadoPedido {

    @Override
    public boolean procesar(Pedido pedido) {
        return true;
    }

}

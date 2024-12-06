package ar.edu.ubp.doo.distribuidoragas.bo;

public class EstadoPedidoCotizado implements EstadoPedido {

    @Override
    public boolean procesar(Pedido pedido) {
        return true;
    }

}

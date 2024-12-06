package ar.edu.ubp.doo.distribuidoragas.bo;

import java.util.List;

public class Factura {
    private int idFactura;
    private int numero;
    private boolean anulada;
    private List<Pago> pago;

    public Factura(int idFactura, int numero, boolean anulada, List<Pago> pago) {
        this.idFactura = idFactura;
        this.numero = numero;
        this.anulada = anulada;
        this.pago = pago;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public List<Pago> getPago() {
        return pago;
    }

    public void setPago(List<Pago> pago) {
        this.pago = pago;
    }
}

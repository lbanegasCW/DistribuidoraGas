package ar.edu.ubp.doo.distribuidoragas.bo;

public class Pago {
    private int idPago;
    private double importe;
    private String datosBancarios;
    private MedioPago medioPago;

    public Pago(int idPago, double importe, String datosBancarios, MedioPago medioPago) {
        this.idPago = idPago;
        this.importe = importe;
        this.datosBancarios = datosBancarios;
        this.medioPago = medioPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getDatosBancarios() {
        return datosBancarios;
    }

    public void setDatosBancarios(String datosBancarios) {
        this.datosBancarios = datosBancarios;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
}

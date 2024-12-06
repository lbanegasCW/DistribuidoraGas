package ar.edu.ubp.doo.distribuidoragas.bo;

public enum MedioPago {
    CONTADO("Contado"),
    CHEQUE("Cheque"),
    ELECTRONICO("Electrónico");

    private final String descripcion;

    MedioPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

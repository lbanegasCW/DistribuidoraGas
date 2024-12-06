package ar.edu.ubp.doo.distribuidoragas.bo;

import java.util.Arrays;
import java.util.List;

public enum PeriodoVenta {

    INVIERNO("I", "Invierno"),
    VERANO("V", "Verano");

    private final String value;
    private final String nombre;

    PeriodoVenta(String value, String nombre) {
        this.value = value;
        this.nombre = nombre;
    }

    public String getValue() {
        return value;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static List<PeriodoVenta> obtenerLista() {
        return Arrays.asList(PeriodoVenta.values());
    }

    public static PeriodoVenta fromValue(String value) {
        for (PeriodoVenta tipo : PeriodoVenta.values()) {
            if (tipo.value.equals(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor no válido para PeriodoVenta: " + value);
    }

}
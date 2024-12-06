package ar.edu.ubp.doo.distribuidoragas.bo;

import java.util.Arrays;
import java.util.List;

public enum TipoDocumento {
    DNI("DNI", "Documento Nacional de Identidad"),
    PASAPORTE("PAS", "Pasaporte"),
    LC("LC", "Libreta Civica"),
    LE("LE", "Libreta de Enrolamiento");

    private final String value;
    private final String nombre;

    TipoDocumento(String value, String nombre) {
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

    public static List<TipoDocumento> obtenerLista() {
        return Arrays.asList(TipoDocumento.values());
    }

    public static TipoDocumento fromValue(String value) {
        for (TipoDocumento tipo : TipoDocumento.values()) {
            if (tipo.value.equals(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor no válido para TipoDocumento: " + value);
    }

}

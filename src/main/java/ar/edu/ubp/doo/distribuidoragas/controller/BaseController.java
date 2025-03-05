package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.dto.DistribuidorDTO;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public abstract class BaseController<T> {
    protected Object view;
    protected int idEntity;

    public abstract void initialize();

    public abstract void cargarEntidadParaEdicion(T entityDTO);

    protected boolean validarCampos(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText() == null || campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    protected void mostrarAlerta(Alert.AlertType tipo, String titulo, String encabezado, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    protected boolean guardarEntidad(T entidad, String nombreEntidad) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Estás seguro de que deseas guardar este " + nombreEntidad + "?");
        confirmAlert.setContentText("Haz clic en 'Sí' para guardar o 'No' para cancelar.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                if (guardarEnBaseDeDatos(entidad)) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, nombreEntidad + " Guardado",
                            "El " + nombreEntidad + " ha sido guardado exitosamente.",
                            "Operación exitosa");
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error al Guardar",
                            "Hubo un error al guardar el " + nombreEntidad + ".",
                            "Por favor, intenta nuevamente.");
                }
            }
        });
        return true;
    }

    public abstract void cargarEntidadParaEdicion(DistribuidorDTO distribuidorDTO);

    protected abstract boolean guardarEnBaseDeDatos(T entidad);

    public void cerrarVentana(Stage stage) {
        stage.close();
    }

    public abstract void setViewParent(Object view);

}


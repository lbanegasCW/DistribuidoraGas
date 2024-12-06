package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.bo.*;
import ar.edu.ubp.doo.distribuidoragas.dto.DistribuidorDTO;
import ar.edu.ubp.doo.distribuidoragas.model.DistribuidorModel;
import ar.edu.ubp.doo.distribuidoragas.model.ZonaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DistribuidorController {
    DistribuidorView view;

    @FXML
    private ComboBox<TipoDocumento> tipoDocumentoComboBox;
    @FXML
    private TextField numeroDocumentoField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private ComboBox<Zona> zonaComboBox;

    private int idDistribuidor;

    public void initialize() {
        try {
            ObservableList<TipoDocumento> tipoDocumentoObservableList =
                    FXCollections.observableArrayList(TipoDocumento.obtenerLista());
            tipoDocumentoComboBox.setItems(tipoDocumentoObservableList);

            ObservableList<Zona> zonasObservableList
                    = FXCollections.observableArrayList(new ZonaModel().obtenerZonas());
            zonaComboBox.setItems(zonasObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setViewParent(DistribuidorView view) {
        this.view = view;
    }

    public void cargarDistribuidorParaEdicion(DistribuidorDTO distribuidorDTO) {
        idDistribuidor = Integer.parseInt(distribuidorDTO.getId());

        tipoDocumentoComboBox.setValue(TipoDocumento.fromValue(distribuidorDTO.getTipoDocumento()));
        numeroDocumentoField.setText(distribuidorDTO.getNroDocumento());
        nombreField.setText(distribuidorDTO.getNombre());
        apellidoField.setText(distribuidorDTO.getApellido());

        Zona selectedZona = zonaComboBox.getItems()
                .stream()
                .filter(zona -> zona.getNombre().equals(distribuidorDTO.getZona()))
                .findFirst()
                .orElse(null);

        zonaComboBox.setValue(selectedZona);
    }

    @FXML
    public void onGuardarDistribuidor() {
        if (numeroDocumentoField.getText() == null || numeroDocumentoField.getText().isEmpty() ||
                nombreField.getText().isEmpty() || apellidoField.getText().isEmpty() ||
                zonaComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Faltan campos");
            alert.setContentText("Por favor complete todos los campos obligatorios.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Estás seguro de que deseas guardar este distribuidor?");
        confirmAlert.setContentText("Haz clic en 'Sí' para guardar o 'No' para cancelar.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                Distribuidor distribuidor = new DistribuidorFactory().crearPersona(
                        tipoDocumentoComboBox.getValue(),
                        numeroDocumentoField.getText(),
                        nombreField.getText(),
                        apellidoField.getText(),
                        zonaComboBox.getValue()
                );

                if (idDistribuidor != 0) {
                    distribuidor.setIdDistribuidor(idDistribuidor);
                }

                boolean success = new DistribuidorModel().guardarDistribuidor(distribuidor);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Distribuidor Guardado");
                    alert.setHeaderText("El distribuidor ha sido guardado exitosamente.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al Guardar");
                    alert.setHeaderText("Hubo un error al guardar el distribuidor.");
                    alert.showAndWait();
                }

                Stage stage = (Stage) apellidoField.getScene().getWindow();
                stage.close();

            } else {
                System.out.println("Operación de guardado cancelada.");
            }
        });

        view.onSearch();
    }

    @FXML
    public void onCancelar() {
        Stage stage = (Stage) apellidoField.getScene().getWindow();
        stage.close();
    }

}

package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.bo.*;
import ar.edu.ubp.doo.distribuidoragas.dto.DistribuidorDTO;
import ar.edu.ubp.doo.distribuidoragas.model.DistribuidorModel;
import ar.edu.ubp.doo.distribuidoragas.model.ZonaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public abstract class DistribuidorController extends BaseController<Distribuidor> {

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

    @Override
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

    @Override
    public void cargarEntidadParaEdicion(DistribuidorDTO distribuidorDTO) {
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
        if (!validarCampos(numeroDocumentoField, nombreField, apellidoField)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Faltan campos", "Por favor complete todos los campos obligatorios.");
            return;
        }

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

        guardarEntidad(distribuidor, "Distribuidor");
    }

    @Override
    protected boolean guardarEnBaseDeDatos(Distribuidor distribuidor) {
        return new DistribuidorModel().guardarDistribuidor(distribuidor);
    }

    @FXML
    public void onCancelar() {
        Stage stage = (Stage) apellidoField.getScene().getWindow();
        cerrarVentana(stage);
    }

    @Override
    public void setViewParent(Object view) {
        this.view = view;
    }

}
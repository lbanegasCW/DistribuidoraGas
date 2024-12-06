package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.bo.*;
import ar.edu.ubp.doo.distribuidoragas.dto.ClienteDTO;
import ar.edu.ubp.doo.distribuidoragas.model.BarrioModel;
import ar.edu.ubp.doo.distribuidoragas.model.ClienteModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ClienteController {
    ClienteView view;

    @FXML
    private ComboBox<TipoDocumento> tipoDocumentoComboBox;
    @FXML
    private TextField numeroDocumentoField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField razonSocialField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField calleField;
    @FXML
    private TextField deptoField;
    @FXML
    private TextField pisoField;
    @FXML
    private ComboBox<Barrio> barrioComboBox;

    private int idCliente;

    public void initialize() {
        try {
            ObservableList<TipoDocumento> tipoDocumentoObservableList =
                    FXCollections.observableArrayList(TipoDocumento.obtenerLista());
            tipoDocumentoComboBox.setItems(tipoDocumentoObservableList);

            ObservableList<Barrio> barriosObservableList
                    = FXCollections.observableArrayList(new BarrioModel().obtenerBarrios());
            barrioComboBox.setItems(barriosObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setViewParent(ClienteView view) {
        this.view = view;
    }

    public void cargarClienteParaEdicion(ClienteDTO clienteDTO) {
        idCliente = Integer.parseInt(clienteDTO.getId());

        tipoDocumentoComboBox.setValue(TipoDocumento.fromValue(clienteDTO.getTipoDocumento()));
        numeroDocumentoField.setText(clienteDTO.getNroDocumento());
        nombreField.setText(clienteDTO.getNombre());
        apellidoField.setText(clienteDTO.getApellido());
        razonSocialField.setText(clienteDTO.getRazonSocial());
        telefonoField.setText(String.valueOf(clienteDTO.getTelefono()));
        calleField.setText(clienteDTO.getCalle());
        deptoField.setText(clienteDTO.getDepto());
        pisoField.setText(String.valueOf(clienteDTO.getPiso()));

        Barrio selectedBarrio = barrioComboBox.getItems()
                .stream()
                .filter(barrio -> barrio.getNombre().equals(clienteDTO.getBarrio()))
                .findFirst()
                .orElse(null);

        barrioComboBox.setValue(selectedBarrio);
    }

    @FXML
    public void onGuardarCliente() {
        if (numeroDocumentoField.getText() == null || numeroDocumentoField.getText().isEmpty() ||
                nombreField.getText().isEmpty() || apellidoField.getText().isEmpty() || telefonoField.getText().isEmpty() ||
                calleField.getText().isEmpty() || deptoField.getText().isEmpty() || pisoField.getText().isEmpty() ||
                barrioComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Faltan campos");
            alert.setContentText("Por favor complete todos los campos obligatorios.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Estás seguro de que deseas guardar este cliente?");
        confirmAlert.setContentText("Haz clic en 'Sí' para guardar o 'No' para cancelar.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                Domicilio domicilio = new Domicilio(
                        calleField.getText(),
                        deptoField.getText(),
                        Integer.parseInt(pisoField.getText()),
                        barrioComboBox.getValue()
                );

                Cliente cliente = new ClienteFactory().crearPersona(
                        tipoDocumentoComboBox.getValue(),
                        numeroDocumentoField.getText(),
                        nombreField.getText(),
                        apellidoField.getText(),
                        razonSocialField.getText(),
                        Long.parseLong(telefonoField.getText()),
                        domicilio
                );

                if (idCliente != 0) {
                    cliente.setIdCliente(idCliente);
                }

                boolean success = new ClienteModel().guardarCliente(cliente);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cliente Guardado");
                    alert.setHeaderText("El cliente ha sido guardado exitosamente.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al Guardar");
                    alert.setHeaderText("Hubo un error al guardar el cliente.");
                    alert.showAndWait();
                }

                Stage stage = (Stage) pisoField.getScene().getWindow();
                stage.close();

            } else {
                System.out.println("Operación de guardado cancelada.");
            }
        });

        view.onSearch();
    }

    @FXML
    public void onCancelar() {
        Stage stage = (Stage) pisoField.getScene().getWindow();
        stage.close();
    }

}

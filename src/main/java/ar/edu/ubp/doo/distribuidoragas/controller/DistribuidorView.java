package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.Main;
import ar.edu.ubp.doo.distribuidoragas.bo.Distribuidor;
import ar.edu.ubp.doo.distribuidoragas.dto.DistribuidorDTO;
import ar.edu.ubp.doo.distribuidoragas.model.DistribuidorModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class DistribuidorView {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<DistribuidorDTO> distribuidoresTable;

    @FXML
    private TableColumn<DistribuidorDTO, String> colId;
    @FXML
    private TableColumn<DistribuidorDTO, String> colTipoDocumento;
    @FXML
    private TableColumn<DistribuidorDTO, String> colNroDocumento;
    @FXML
    private TableColumn<DistribuidorDTO, String> colNombre;
    @FXML
    private TableColumn<DistribuidorDTO, String> colApellido;
    @FXML
    private TableColumn<DistribuidorDTO, String> colZona;

    private Button distribuidoresButton;

    public DistribuidorView() {
        distribuidoresButton = new Button("Ver Distribuidores");
    }

    @FXML
    public void initialize() {
        List<Distribuidor> distribuidores = new DistribuidorModel().buscarDistribuidores(searchField.getText());

        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colTipoDocumento.setCellValueFactory(cellData -> cellData.getValue().tipoDocumentoProperty());
        colNroDocumento.setCellValueFactory(cellData -> cellData.getValue().nroDocumentoProperty());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colZona.setCellValueFactory(cellData -> cellData.getValue().zonaProperty());

        distribuidoresTable.getItems().addAll(distribuidores.stream().map(DistribuidorDTO::new).toList());
    }

    @FXML
    public void onSearch() {
        List<Distribuidor> distribuidores = new DistribuidorModel().buscarDistribuidores(searchField.getText());
        distribuidoresTable.getItems().clear();
        distribuidoresTable.getItems().addAll(distribuidores.stream().map(DistribuidorDTO::new).toList());
    }

    @FXML
    private void onNuevoDistribuidor() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("distribuidores-crud.fxml"));
            Parent root = loader.load();

            DistribuidorController distribuidorControllerForm = loader.getController();
            if (distribuidorControllerForm == null) {
                System.out.println("Error al obtener el controlador.");
                return;
            }

            distribuidorControllerForm.setViewParent(this);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Nuevo Distribuidor");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de distribuidor: " + e.getMessage());
        }
    }

    @FXML
    private void onModificarDistribuidor() {
        DistribuidorDTO selected = distribuidoresTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("distribuidores-crud.fxml"));
            Parent root = loader.load();

            DistribuidorController distribuidorControllerForm = loader.getController();
            if (distribuidorControllerForm == null) {
                System.out.println("Error al obtener el controlador.");
                return;
            }

            distribuidorControllerForm.setViewParent(this);
            distribuidorControllerForm.cargarDistribuidorParaEdicion(selected);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Modificar Distribuidor");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de distribuidores: " + e.getMessage());
        }
    }

    public void show(Stage stage) {
        StackPane root = new StackPane();
        root.getChildren().add(distribuidoresButton);
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

}

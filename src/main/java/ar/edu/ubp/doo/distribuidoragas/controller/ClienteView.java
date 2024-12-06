package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.Main;
import ar.edu.ubp.doo.distribuidoragas.bo.Cliente;
import ar.edu.ubp.doo.distribuidoragas.dto.ClienteDTO;
import ar.edu.ubp.doo.distribuidoragas.model.ClienteModel;
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

public class ClienteView {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<ClienteDTO> clientesTable;

    @FXML
    private TableColumn<ClienteDTO, String> colId;
    @FXML
    private TableColumn<ClienteDTO, String> colTipoDocumento;
    @FXML
    private TableColumn<ClienteDTO, String> colNroDocumento;
    @FXML
    private TableColumn<ClienteDTO, String> colNombre;
    @FXML
    private TableColumn<ClienteDTO, String> colApellido;
    @FXML
    private TableColumn<ClienteDTO, String> colRazonSocial;
    @FXML
    private TableColumn<ClienteDTO, String> colTelefono;
    @FXML
    private TableColumn<ClienteDTO, String> colCalle;
    @FXML
    private TableColumn<ClienteDTO, String> colDepto;
    @FXML
    private TableColumn<ClienteDTO, String> colPiso;
    @FXML
    private TableColumn<ClienteDTO, String> colBarrio;

    private Button clientesButton;

    public ClienteView() {
        clientesButton = new Button("Ver Clientes");
    }

    @FXML
    public void initialize() {
        List<Cliente> cliente = new ClienteModel().buscarClientes(searchField.getText());

        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colTipoDocumento.setCellValueFactory(cellData -> cellData.getValue().tipoDocumentoProperty());
        colNroDocumento.setCellValueFactory(cellData -> cellData.getValue().nroDocumentoProperty());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colRazonSocial.setCellValueFactory(cellData -> cellData.getValue().razonSocialProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        colCalle.setCellValueFactory(cellData -> cellData.getValue().calleProperty());
        colDepto.setCellValueFactory(cellData -> cellData.getValue().deptoProperty());
        colPiso.setCellValueFactory(cellData -> cellData.getValue().pisoProperty());
        colBarrio.setCellValueFactory(cellData -> cellData.getValue().barrioProperty());

        clientesTable.getItems().addAll(cliente.stream().map(ClienteDTO::new).toList());
    }

    @FXML
    public void onSearch() {
        List<Cliente> cliente = new ClienteModel().buscarClientes(searchField.getText());
        clientesTable.getItems().clear();
        clientesTable.getItems().addAll(cliente.stream().map(ClienteDTO::new).toList());
    }

    @FXML
    private void onNuevoCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("clientes-crud.fxml"));
            Parent root = loader.load();

            ClienteController clienteControllerForm = loader.getController();
            if (clienteControllerForm == null) {
                System.out.println("Error al obtener el controlador.");
                return;
            }

            clienteControllerForm.setViewParent(this);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Nuevo cliente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de clientes: " + e.getMessage());
        }
    }

    @FXML
    private void onModificarCliente() {
        ClienteDTO selected = clientesTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("clientes-crud.fxml"));
            Parent root = loader.load();

            ClienteController clienteControllerForm = loader.getController();
            if (clienteControllerForm == null) {
                System.out.println("Error al obtener el controlador.");
                return;
            }

            clienteControllerForm.setViewParent(this);
            clienteControllerForm.cargarClienteParaEdicion(selected);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Modificar cliente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de clientes: " + e.getMessage());
        }
    }

    public void show(Stage stage) {
        StackPane root = new StackPane();
        root.getChildren().add(clientesButton);
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

}

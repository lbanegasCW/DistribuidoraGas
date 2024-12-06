package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.Main;
import ar.edu.ubp.doo.distribuidoragas.bo.Cliente;
import ar.edu.ubp.doo.distribuidoragas.bo.ClienteFactory;
import ar.edu.ubp.doo.distribuidoragas.bo.Domicilio;
import ar.edu.ubp.doo.distribuidoragas.bo.Pedido;
import ar.edu.ubp.doo.distribuidoragas.dto.ClienteDTO;
import ar.edu.ubp.doo.distribuidoragas.dto.PedidoDTO;
import ar.edu.ubp.doo.distribuidoragas.model.ClienteModel;
import ar.edu.ubp.doo.distribuidoragas.model.PedidoModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class PedidoView {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<PedidoDTO> pedidosTable;

    @FXML
    private TableColumn<PedidoDTO, String> colId;
    @FXML
    private TableColumn<PedidoDTO, String> colnombreCliente;
    @FXML
    private TableColumn<PedidoDTO, String> getColApellido;
    @FXML
    private TableColumn<PedidoDTO, String> colRazonSocial;
    @FXML
    private TableColumn<PedidoDTO, String> colCalle;
    @FXML
    private TableColumn<PedidoDTO, String> colDepto;
    @FXML
    private TableColumn<PedidoDTO, String> colPiso;
    @FXML
    private TableColumn<PedidoDTO, String> colProductos;
    @FXML
    private TableColumn<PedidoDTO, String> colZona;

    private Button pedidosButton;

    public PedidoView() {
        pedidosButton = new Button("Ver pedidos");
    }

    @FXML
    public void initialize() {
        List<Pedido> pedidos = new PedidoModel().buscarPedidos(searchField.getText());

        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colnombreCliente.setCellValueFactory(cellData -> cellData.getValue().nombreClienteProperty());
        getColApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoClienteProperty());
        colRazonSocial.setCellValueFactory(cellData -> cellData.getValue().razonSocialProperty());
        colCalle.setCellValueFactory(cellData -> cellData.getValue().calleProperty());
        colDepto.setCellValueFactory(cellData -> cellData.getValue().deptoProperty());
        colPiso.setCellValueFactory(cellData -> cellData.getValue().pisoProperty());
        colProductos.setCellValueFactory(cellData -> cellData.getValue().productosProperty());
        colZona.setCellValueFactory(cellData -> cellData.getValue().zonaProperty());

        pedidosTable.getItems().addAll(pedidos.stream().map(PedidoDTO::new).toList());
    }

    @FXML
    public void onSearch() {
        List<Pedido> pedidos = new PedidoModel().buscarPedidos(searchField.getText());
        pedidosTable.getItems().clear();
        pedidosTable.getItems().addAll(pedidos.stream().map(PedidoDTO::new).toList());
    }

    @FXML
    private void onNuevoPedido() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("pedidos-crud.fxml"));
            Parent root = loader.load();

            PedidoController pedidoControllerForm = loader.getController();
            if (pedidoControllerForm == null) {
                System.out.println("Error al obtener el controlador.");
                return;
            }

            pedidoControllerForm.setViewParent(this);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Nuevo Pedido");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de pedidos: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelarPedido() {
        /*ClienteDTO selected = pedidosTable.getSelectionModel().getSelectedItem();
        try {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmación");
            confirmAlert.setHeaderText("¿Estás seguro de que deseas cancelar este pedido?");
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
        }*/
    }

    public void show(Stage stage) {
        StackPane root = new StackPane();
        root.getChildren().add(pedidosButton);
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

}

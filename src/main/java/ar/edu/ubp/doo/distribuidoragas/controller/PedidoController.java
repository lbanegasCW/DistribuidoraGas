package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.bo.*;
import ar.edu.ubp.doo.distribuidoragas.dto.ClienteDTO;
import ar.edu.ubp.doo.distribuidoragas.model.BarrioModel;
import ar.edu.ubp.doo.distribuidoragas.model.ClienteModel;
import ar.edu.ubp.doo.distribuidoragas.model.ProductosModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    PedidoView view;

    @FXML
    private TextField observaciones;
    @FXML
    private ComboBox<Cliente> clienteCombobox;
    @FXML
    private ListView<CheckBox> listViewProductos;

    private int idCliente;
    private List<Producto> productosSeleccionados = new ArrayList<>();

    public void initialize() {
        ObservableList<Cliente> clientesObservableList
                = FXCollections.observableArrayList(new ClienteModel().obtenerClientes());
        clienteCombobox.setItems(clientesObservableList);

        List<Producto> productos = new ProductosModel().obtenerProductos();

        for (Producto producto : productos) {
            CheckBox checkBox = new CheckBox(producto.toString());
            checkBox.setUserData(producto);
            listViewProductos.getItems().add(checkBox);
        }

        listViewProductos.setOnMouseClicked(event -> {
            productosSeleccionados.clear();
            for (CheckBox checkBox : listViewProductos.getItems()) {
                if (checkBox.isSelected()) {
                    productosSeleccionados.add((Producto) checkBox.getUserData());
                }
            }
        });
    }

    public List<Producto> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public void setViewParent(PedidoView view) {
        this.view = view;
    }

    @FXML
    public void onGuardarPedido() {
        validarFormulario(() -> {
            System.out.println("El pedido ha sido guardado exitosamente.");
            List<PedidoDetalle> pedidoDetalles = new ArrayList<>();
            for (Producto producto: productosSeleccionados) {
                PedidoDetalle pedidoDetalle = new PedidoDetalle();
                pedidoDetalle.setCantidad(1); //TODO: Cantidad
                pedidoDetalle.setPrecio(producto.getPrecio());
                pedidoDetalle.setProducto(producto);
                pedidoDetalles.add(pedidoDetalle);
            }

            Pedido pedido = new Pedido(new EstadoPedidoCotizado());
            pedido.setPedidoDetalles(pedidoDetalles);

            if (pedido.procesar()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pedido Guardado");
                alert.setHeaderText("El pedido ha sido guardado exitosamente.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al Guardar");
                alert.setHeaderText("Hubo un error al guardar el cliente.");
                alert.showAndWait();
            }

            Stage stage = (Stage) observaciones.getScene().getWindow();
            stage.close();
        });
    }

    public void validarFormulario(Runnable callback) {
        if (observaciones.getText() == null || observaciones.getText().isEmpty() ||
                clienteCombobox.getValue() == null || !getProductosSeleccionados().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Faltan campos");
            alert.setContentText("Por favor complete todos los campos obligatorios.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Estás seguro de que deseas guardar este pedido?");
        confirmAlert.setContentText("Haz clic en 'Sí' para guardar o 'No' para cancelar.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                callback.run();
            } else {
                System.out.println("Operación de guardado cancelada.");
            }
        });

        view.onSearch();
    }

    @FXML
    public void onCancelar() {
        Stage stage = (Stage) observaciones.getScene().getWindow();
        stage.close();
    }

}

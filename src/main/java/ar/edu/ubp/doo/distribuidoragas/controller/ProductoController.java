package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.bo.Producto;
import ar.edu.ubp.doo.distribuidoragas.dto.ProductoDTO;
import ar.edu.ubp.doo.distribuidoragas.model.ProductosModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductoController {
    ProductoView view;

    @FXML
    private TextField nombre;
    @FXML
    private TextField tamano;
    @FXML
    private TextField unidadMedida;
    @FXML
    private TextField precio;

    private int idProducto;

    public void initialize() {}

    public void setViewParent(ProductoView view) {
        this.view = view;
    }

    public void cargarProductoParaEdicion(ProductoDTO productoDTO) {
        idProducto = Integer.parseInt(productoDTO.getId());

        nombre.setText(productoDTO.getNombre());
        tamano.setText(productoDTO.getTamano());
        unidadMedida.setText(productoDTO.getUnidadMedida());
        precio.setText(productoDTO.getPrecio());
    }

    @FXML
    public void onGuardarProducto() {
        if (nombre.getText() == null || nombre.getText().isEmpty() ||
            precio.getText() == null || precio.getText().isEmpty() ||
            tamano.getText().isEmpty() || unidadMedida.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Faltan campos");
            alert.setContentText("Por favor complete todos los campos obligatorios.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Estás seguro de que deseas guardar este producto?");
        confirmAlert.setContentText("Haz clic en 'Sí' para guardar o 'No' para cancelar.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                Producto producto =
                        new Producto(nombre.getText(), Integer.parseInt(tamano.getText()),
                                unidadMedida.getText(), Double.parseDouble(precio.getText()));

                if (idProducto != 0) {
                    producto.setIdProducto(idProducto);
                }

                boolean success = new ProductosModel().guardarProducto(producto);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Producto Guardado");
                    alert.setHeaderText("El producto ha sido guardado exitosamente.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al Guardar");
                    alert.setHeaderText("Hubo un error al guardar el producto.");
                    alert.showAndWait();
                }

                Stage stage = (Stage) unidadMedida.getScene().getWindow();
                stage.close();

            } else {
                System.out.println("Operación de guardado cancelada.");
            }
        });

        view.onSearch();
    }

    @FXML
    public void onCancelar() {
        Stage stage = (Stage) unidadMedida.getScene().getWindow();
        stage.close();
    }

}

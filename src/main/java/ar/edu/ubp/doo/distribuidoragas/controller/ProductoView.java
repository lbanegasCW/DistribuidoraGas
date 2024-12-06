package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.Main;
import ar.edu.ubp.doo.distribuidoragas.bo.Producto;
import ar.edu.ubp.doo.distribuidoragas.dto.ProductoDTO;
import ar.edu.ubp.doo.distribuidoragas.model.ProductosModel;
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

public class ProductoView {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<ProductoDTO> productosTable;

    @FXML
    private TableColumn<ProductoDTO, String> colId;
    @FXML
    private TableColumn<ProductoDTO, String> colNombre;
    @FXML
    private TableColumn<ProductoDTO, String> colTamano;
    @FXML
    private TableColumn<ProductoDTO, String> colUnidaMedida;
    @FXML
    private TableColumn<ProductoDTO, String> colPrecio;


    private Button productosButton;

    public ProductoView() {
        productosButton = new Button("Ver Productos");
    }

    @FXML
    public void initialize() {
        List<Producto> productos = new ProductosModel().buscarProductos(searchField.getText());

        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colTamano.setCellValueFactory(cellData -> cellData.getValue().tamanoProperty());
        colUnidaMedida.setCellValueFactory(cellData -> cellData.getValue().unidadMedidaProperty());
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());

        productosTable.getItems().addAll(productos.stream().map(ProductoDTO::new).toList());
    }

    @FXML
    public void onSearch() {
        List<Producto> productos = new ProductosModel().buscarProductos(searchField.getText());
        productosTable.getItems().clear();
        productosTable.getItems().addAll(productos.stream().map(ProductoDTO::new).toList());
    }

    @FXML
    private void onNuevoProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("productos-crud.fxml"));
            Parent root = loader.load();

            ProductoController productoControllerForm = loader.getController();
            if (productoControllerForm == null) {
                System.out.println("Error al obtener el controlador.");
                return;
            }

            productoControllerForm.setViewParent(this);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Nuevo Producto");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de producto: " + e.getMessage());
        }
    }

    @FXML
    private void onModificarProducto() {
        ProductoDTO selected = productosTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("productos-crud.fxml"));
            Parent root = loader.load();

            ProductoController productoControllerForm = loader.getController();
            if (productoControllerForm == null) {
                System.out.println("Error al obtener el controlador.");
                return;
            }

            productoControllerForm.setViewParent(this);
            productoControllerForm.cargarProductoParaEdicion(selected);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Modificar Producto");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de productos: " + e.getMessage());
        }
    }

    public void show(Stage stage) {
        StackPane root = new StackPane();
        root.getChildren().add(productosButton);
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

}

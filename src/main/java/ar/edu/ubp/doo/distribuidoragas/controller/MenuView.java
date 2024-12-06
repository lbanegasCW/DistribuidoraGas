package ar.edu.ubp.doo.distribuidoragas.controller;

import ar.edu.ubp.doo.distribuidoragas.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuView {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void abrirPantallaClientes() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("clientes-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Gestión de Clientes");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de clientes: " + e.getMessage());
        }
    }

    @FXML
    protected void abrirPantallaDistribuidores() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("distribuidores-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Gestión de Distribuidores");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de distribuidores: " + e.getMessage());
        }
    }

    @FXML
    protected void abrirPantallaProductos() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("productos-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Gestión de Productos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de productos: " + e.getMessage());
        }
    }

    @FXML
    protected void abrirPantallaPedidos() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("pedidos-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Gestión de Pedidos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir la pantalla de pedidos: " + e.getMessage());
        }
    }

}
package com.oregontrail.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.oregontrail.utils.GameManager;
import java.net.URL;
import java.util.ResourceBundle;

public class TiendaController implements Initializable {
    @FXML private Label lblDinero, lblTotal;
    @FXML private Spinner<Integer> spnComida, spnMuniciones, spnRopa, spnRuedas, spnEjes, spnLenguas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarSpinners();
        actualizarDinero();
        agregarListeners();
    }

    private void configurarSpinners() {
        spnComida.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2000, 0, 50));
        spnMuniciones.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 0, 20));
        spnRopa.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0, 1));
        spnRuedas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 3, 1));
        spnEjes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 2, 1));
        spnLenguas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 2, 1));
    }

    private void agregarListeners() {
        spnComida.valueProperty().addListener((obs, old, nue) -> calcularTotal());
        spnMuniciones.valueProperty().addListener((obs, old, nue) -> calcularTotal());
        spnRopa.valueProperty().addListener((obs, old, nue) -> calcularTotal());
        spnRuedas.valueProperty().addListener((obs, old, nue) -> calcularTotal());
        spnEjes.valueProperty().addListener((obs, old, nue) -> calcularTotal());
        spnLenguas.valueProperty().addListener((obs, old, nue) -> calcularTotal());
    }

    private void calcularTotal() {
        double total = spnComida.getValue() * 0.20 +
                spnMuniciones.getValue() * 2.00 +
                spnRopa.getValue() * 10.00 +
                spnRuedas.getValue() * 10.00 +
                spnEjes.getValue() * 10.00 +
                spnLenguas.getValue() * 10.00;
        lblTotal.setText(String.format("Total: $%.2f", total));
    }

    private void actualizarDinero() {
        lblDinero.setText(String.format("Dinero: $%.0f", GameManager.getInstance().getJugador().getDinero()));
    }

    @FXML
    private void handleComprar() {
        double total = spnComida.getValue() * 0.20 +
                spnMuniciones.getValue() * 2.00 +
                spnRopa.getValue() * 10.00 +
                spnRuedas.getValue() * 10.00 +
                spnEjes.getValue() * 10.00 +
                spnLenguas.getValue() * 10.00;

        if (total > GameManager.getInstance().getJugador().getDinero()) {
            mostrarAlerta("No tienes suficiente dinero");
            return;
        }

        if (spnComida.getValue() < 200) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Parece que no compraste mucha comida. ¿Estás seguro?");
            if (alert.showAndWait().get() != ButtonType.OK) return;
        }

        GameManager.getInstance().getJugador().gastarDinero(total);
        GameManager.getInstance().getCaravana().setComida(spnComida.getValue());
        GameManager.getInstance().getCaravana().setMuniciones(spnMuniciones.getValue());
        GameManager.getInstance().getCaravana().setRopa(spnRopa.getValue());
        GameManager.getInstance().getCaravana().setRuedas(spnRuedas.getValue());
        GameManager.getInstance().getCaravana().setEjes(spnEjes.getValue());
        GameManager.getInstance().getCaravana().setLenguas(spnLenguas.getValue());

        cargarVista("/views/viaje.fxml", "En el Camino");
    }

    @FXML
    private void handleVolver() {
        cargarVista("/views/configuracion.fxml", "Configuración");
    }

    private void cargarVista(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) lblDinero.getScene().getWindow();
            Scene scene = new Scene(root, 900, 700);
            stage.setTitle(titulo);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
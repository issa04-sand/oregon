package com.oregontrail.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.oregontrail.models.Evento;
import com.oregontrail.utils.GameManager;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViajeController implements Initializable {
    @FXML private Label lblFecha, lblClima, lblUbicacion, lblDistancia;
    @FXML private Label lblDinero, lblComida, lblMuniciones, lblRopa, lblSalud, lblMiembros;
    @FXML private ProgressBar progressBar;
    @FXML private TextArea txtEvento;
    
    private GameManager gm;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gm = GameManager.getInstance();
        actualizarUI();
        txtEvento.setText("Comienzas tu viaje desde Independence, Missouri.\n" +
                         "Destino: Oregon City - 2000 millas al oeste.\n\n" +
                         "¡Buena suerte en tu viaje!");
    }
    
    @FXML
    private void handleContinuar() {
        if (verificarEstado()) return;
        
        gm.avanzarDia();
        
        if (Math.random() < 0.3) {
            Evento evento = gm.generarEventoAleatorio();
            txtEvento.setText("DÍA " + gm.getDiaActual() + "\n\n" +
                            evento.getDescripcion() + "\n\n" +
                            "Efecto: " + evento.getEfecto());
            aplicarEfectoEvento(evento);
        } else {
            txtEvento.setText("DÍA " + gm.getDiaActual() + "\n\n" +
                            "Avanzas por el camino sin problemas.\n" +
                            "El viaje continúa...");
        }
        
        actualizarUI();
        verificarEstado();
    }
    
    private void aplicarEfectoEvento(Evento evento) {
        switch(evento.getTipo()) {
            case "Accidente":
                gm.getCaravana().setRuedas(Math.max(0, gm.getCaravana().getRuedas() - 1));
                break;
            case "Fortuna":
                gm.getCaravana().setComida(gm.getCaravana().getComida() + 50);
                break;
        }
    }
    
    @FXML
    private void handleDescansar() {
        txtEvento.setText("Tu caravana descansa por un día.\n\n" +
                         "La salud de todos mejora un poco.\n" +
                         "Se consumió comida durante el descanso.");
        
        gm.getCaravana().consumirComida();
        gm.getJugador().setSalud(Math.min(100, gm.getJugador().getSalud() + 10));
        gm.getCaravana().getMiembros().forEach(m -> m.setSalud(Math.min(100, m.getSalud() + 10)));
        
        actualizarUI();
    }
    
    @FXML
    private void handleCazar() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Cazar");
        dialog.setHeaderText("¿Cuántas municiones usarás?");
        dialog.setContentText("Municiones:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(mun -> {
            try {
                int municiones = Integer.parseInt(mun);
                int comida = gm.cazarAnimales(municiones);
                if (comida > 0) {
                    txtEvento.setText("¡Caza exitosa!\n\n" +
                                    "Usaste " + municiones + " municiones.\n" +
                                    "Obtuviste " + comida + " libras de carne.");
                } else {
                    txtEvento.setText("No tienes suficientes municiones para cazar.");
                }
                actualizarUI();
            } catch (NumberFormatException e) {
                mostrarAlerta("Ingresa un número válido");
            }
        });
    }
    
    @FXML
    private void handleEstado() {
        String estado = "ESTADO DE LA CARAVANA\n\n";
        estado += "Líder: " + gm.getJugador().getNombre() + "\n";
        estado += "Salud: " + gm.getJugador().getSalud() + "%\n\n";
        estado += "Miembros:\n";
        for (var m : gm.getCaravana().getMiembros()) {
            estado += "- " + m.getNombre() + " (Salud: " + m.getSalud() + "%";
            if (m.estaEnfermo()) estado += ", " + m.getEnfermedad();
            estado += ")\n";
        }
        estado += "\nRitmo: " + gm.getCaravana().getRitmo();
        estado += "\nRaciones: " + gm.getCaravana().getRaciones();
        
        txtEvento.setText(estado);
    }
    
    private boolean verificarEstado() {
        if (gm.haGanado()) {
            cargarVista("/views/victoria.fxml", "¡Victoria!");
            return true;
        }
        
        if (gm.haPerdido()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Game Over");
            alert.setHeaderText("Has perdido");
            alert.setContentText("Tu caravana no pudo completar el viaje.");
            alert.showAndWait();
            cargarVista("/views/inicio.fxml", "Oregon Trail");
            return true;
        }
        
        return false;
    }
    
    private void actualizarUI() {
        lblFecha.setText(gm.getDiaActual() + " de " + gm.getMesActual());
        lblClima.setText(gm.getClima());
        lblUbicacion.setText(gm.getUbicacionActual());
        lblDistancia.setText(String.format("%.0f / %.0f millas", 
            gm.getJugador().getDistanciaRecorrida(), gm.getDistanciaTotal()));
        
        progressBar.setProgress(gm.getJugador().getDistanciaRecorrida() / gm.getDistanciaTotal());
        
        lblDinero.setText(String.format("$%.0f", gm.getJugador().getDinero()));
        lblComida.setText(gm.getCaravana().getComida() + " lbs");
        lblMuniciones.setText(String.valueOf(gm.getCaravana().getMuniciones()));
        lblRopa.setText(String.valueOf(gm.getCaravana().getRopa()));
        lblSalud.setText(gm.getJugador().getSalud() + "%");
        lblMiembros.setText(gm.getCaravana().getMiembrosVivos() + "/" + gm.getCaravana().getMiembros().size());
    }
    
    private void cargarVista(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) lblFecha.getScene().getWindow();
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

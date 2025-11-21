package com.oregontrail.utils;
import com.oregontrail.models.*;
import java.util.*;

public class GameManager {
    private static GameManager instance;
    private Jugador jugador;
    private Caravana caravana;
    private int diaActual = 1;
    private String mesActual = "Marzo";
    private String clima = "Soleado";
    private String ubicacionActual = "Independence, Missouri";
    private double distanciaTotal = 2000;
    private Random random = new Random();
    
    private GameManager() {}
    
    public static GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }
    
    public void iniciarJuego(String nombre, String profesion) {
        jugador = new Jugador(nombre, profesion);
        caravana = new Caravana();
        diaActual = 1;
        mesActual = "Marzo";
        ubicacionActual = "Independence, Missouri";
    }
    
    public Jugador getJugador() { return jugador; }
    public Caravana getCaravana() { return caravana; }
    public int getDiaActual() { return diaActual; }
    public String getMesActual() { return mesActual; }
    public String getClima() { return clima; }
    public String getUbicacionActual() { return ubicacionActual; }
    public double getDistanciaTotal() { return distanciaTotal; }
    
    public void avanzarDia() {
        diaActual++;
        double distanciaAvanzada = caravana.getVelocidad() * (random.nextDouble() * 0.5 + 0.75);
        jugador.setDistanciaRecorrida(jugador.getDistanciaRecorrida() + distanciaAvanzada);
        
        caravana.consumirComida();
        actualizarClima();
        verificarEventos();
        
        if (diaActual % 30 == 0) cambiarMes();
    }
    
    private void actualizarClima() {
        String[] climas = {"Soleado", "Nublado", "Lluvioso", "Ventoso", "Tormentoso"};
        clima = climas[random.nextInt(climas.length)];
    }
    
    private void cambiarMes() {
        String[] meses = {"Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre"};
        int mesIndex = Arrays.asList(meses).indexOf(mesActual);
        if (mesIndex < meses.length - 1) mesActual = meses[mesIndex + 1];
    }
    
    public void verificarEventos() {
        if (random.nextInt(100) < 15) {
            caravana.getMiembros().stream()
                .filter(m -> random.nextInt(100) < 10)
                .forEach(m -> {
                    String[] enfermedades = {"Cólera", "Disentería", "Fiebre", "Sarampión"};
                    m.setEnfermedad(enfermedades[random.nextInt(enfermedades.length)]);
                    m.setSalud(m.getSalud() - 20);
                });
        }
    }
    
    public Evento generarEventoAleatorio() {
        int tipo = random.nextInt(5);
        switch(tipo) {
            case 0: return new Evento("Clima", "Tormenta severa retrasa el viaje", "Velocidad -2");
            case 1: return new Evento("Encuentro", "Encontraste un viajero amigable", "Información útil");
            case 2: return new Evento("Accidente", "Se rompió una rueda del carromato", "Rueda -1");
            case 3: return new Evento("Fortuna", "Encontraste suministros abandonados", "Comida +50");
            default: return new Evento("Descanso", "Día tranquilo en el camino", "Sin efectos");
        }
    }
    
    public int cazarAnimales(int municionesUsadas) {
        if (caravana.getMuniciones() < municionesUsadas) return 0;
        caravana.setMuniciones(caravana.getMuniciones() - municionesUsadas);
        int comidaObtenida = random.nextInt(100) + 50;
        caravana.setComida(caravana.getComida() + comidaObtenida);
        return comidaObtenida;
    }
    
    public boolean haPerdido() {
        return !jugador.estaVivo() || caravana.getMiembrosVivos() == 0 || caravana.getComida() <= 0;
    }
    
    public boolean haGanado() {
        return jugador.getDistanciaRecorrida() >= distanciaTotal;
    }
}

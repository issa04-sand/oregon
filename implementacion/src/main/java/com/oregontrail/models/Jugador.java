package com.oregontrail.models;
public class Jugador {
    private String nombre, profesion;
    private double dinero, distanciaRecorrida;
    private int salud;
    public Jugador(String n, String p) {
        this.nombre = n; this.profesion = p; this.salud = 100; this.distanciaRecorrida = 0;
        this.dinero = p.equals("Banquero") ? 1600 : p.equals("Carpintero") ? 800 : 400;
    }
    public String getNombre() { return nombre; }
    public String getProfesion() { return profesion; }
    public double getDinero() { return dinero; }
    public void setDinero(double d) { dinero = d; }
    public int getSalud() { return salud; }
    public void setSalud(int s) { salud = Math.max(0, Math.min(100, s)); }
    public double getDistanciaRecorrida() { return distanciaRecorrida; }
    public void setDistanciaRecorrida(double d) { distanciaRecorrida = d; }
    public boolean estaVivo() { return salud > 0; }
    public void gastarDinero(double c) { dinero -= c; }
}

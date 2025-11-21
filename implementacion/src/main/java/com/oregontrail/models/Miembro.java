package com.oregontrail.models;
public class Miembro {
    private String nombre, enfermedad = "Ninguna";
    private int edad, salud = 100;
    public Miembro(String n, int e) { nombre = n; edad = e; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public int getSalud() { return salud; }
    public void setSalud(int s) { salud = Math.max(0, Math.min(100, s)); }
    public String getEnfermedad() { return enfermedad; }
    public void setEnfermedad(String e) { enfermedad = e; }
    public boolean estaVivo() { return salud > 0; }
    public boolean estaEnfermo() { return !enfermedad.equals("Ninguna"); }
}

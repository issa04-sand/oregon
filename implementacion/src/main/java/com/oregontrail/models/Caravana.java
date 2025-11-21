package com.oregontrail.models;
import java.util.*;
public class Caravana {
    private List<Miembro> miembros = new ArrayList<>();
    private int comida, municiones, ropa, ruedas = 3, ejes = 2, lenguas = 2;
    private double velocidad = 5.0;
    private String ritmo = "Normal", raciones = "Completas";
    public void agregarMiembro(Miembro m) { miembros.add(m); }
    public List<Miembro> getMiembros() { return miembros; }
    public int getComida() { return comida; }
    public void setComida(int c) { comida = Math.max(0, c); }
    public int getMuniciones() { return municiones; }
    public void setMuniciones(int m) { municiones = Math.max(0, m); }
    public int getRopa() { return ropa; }
    public void setRopa(int r) { ropa = Math.max(0, r); }
    public int getRuedas() { return ruedas; }
    public void setRuedas(int r) { ruedas = r; }
    public int getEjes() { return ejes; }
    public void setEjes(int e) { ejes = e; }
    public int getLenguas() { return lenguas; }
    public void setLenguas(int l) { lenguas = l; }
    public double getVelocidad() { return velocidad; }
    public String getRitmo() { return ritmo; }
    public void setRitmo(String r) { 
        ritmo = r;
        velocidad = r.equals("Constante") ? 3.0 : r.equals("Rápido") ? 7.0 : 5.0;
    }
    public String getRaciones() { return raciones; }
    public void setRaciones(String r) { raciones = r; }
    public int getMiembrosVivos() { return (int)miembros.stream().filter(Miembro::estaVivo).count(); }
    public void consumirComida() {
        int consumo = getMiembrosVivos() * (raciones.equals("Escasas") ? 1 : raciones.equals("Pobres") ? 2 : 3);
        setComida(getComida() - consumo);
    }
}

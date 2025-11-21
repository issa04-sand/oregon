#!/bin/bash

BASE="/home/claude/OregonTrail/src/main/java/com/oregontrail"

# ============= MODELOS =============
cat > $BASE/models/Jugador.java << 'EOFJ'
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
EOFJ

cat > $BASE/models/Caravana.java << 'EOFC'
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
EOFC

cat > $BASE/models/Miembro.java << 'EOFM'
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
EOFM

cat > $BASE/models/Evento.java << 'EOFE'
package com.oregontrail.models;
public class Evento {
    private String tipo, descripcion, efecto;
    public Evento(String t, String d, String e) { tipo = t; descripcion = d; efecto = e; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public String getEfecto() { return efecto; }
}
EOFE

echo "✓ Modelos creados"

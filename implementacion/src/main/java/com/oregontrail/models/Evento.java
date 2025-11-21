package com.oregontrail.models;
public class Evento {
    private String tipo, descripcion, efecto;
    public Evento(String t, String d, String e) { tipo = t; descripcion = d; efecto = e; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public String getEfecto() { return efecto; }
}

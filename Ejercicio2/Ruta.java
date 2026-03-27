package com.mycompany.ejercicio2paralela;

public class Ruta {
    private final String origen;
    private final String destino;

    public Ruta(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
}
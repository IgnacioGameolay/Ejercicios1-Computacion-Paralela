package com.mycompany.ejercicio3;
public class TareaProcesamiento extends Thread {
    private final String nombreTarea;

    public TareaProcesamiento(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    @Override
    public void run() {
        System.out.println("Iniciando " + nombreTarea + " en hilo: " + Thread.currentThread().getName());

        for (int i = 1; i <= 3; i++) {
            System.out.println(nombreTarea + " procesando bloque " + i + " en hilo: " + Thread.currentThread().getName());
        }

        System.out.println("Finalizando " + nombreTarea + " en hilo: " + Thread.currentThread().getName());
    }
}
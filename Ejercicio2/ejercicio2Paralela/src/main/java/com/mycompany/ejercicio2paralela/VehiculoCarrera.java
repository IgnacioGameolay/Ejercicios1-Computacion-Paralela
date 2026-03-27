package com.mycompany.ejercicio2paralela;
import java.util.Random;

public class VehiculoCarrera extends Thread {
    private final String nombreVehiculo;
    private final Ruta ruta;
    private final Random random;

    public VehiculoCarrera(String nombreVehiculo, Ruta ruta) {
        this.nombreVehiculo = nombreVehiculo;
        this.ruta = ruta;
        this.random = new Random();
    }

    @Override


    public void run() {
        System.out.println(nombreVehiculo + " inició su viaje desde " + ruta.getOrigen() + " hacia " + ruta.getDestino());

        int tiempoViaje = generarTiempoDeViaje();

        try {
            Thread.sleep(tiempoViaje);
        } catch (InterruptedException e) {
            System.out.println("El auto " + nombreVehiculo + " fue interrumpido durante el trayecto.");
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println(nombreVehiculo + " llegó a la meta en " + tiempoViaje + " ms");
    }

    private int generarTiempoDeViaje() {
        return random.nextInt(4000) + 1000;
    }
}
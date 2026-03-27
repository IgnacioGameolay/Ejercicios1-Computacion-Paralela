package com.mycompany.ejercicio2paralela;

public class ControlCarrera {
    public void iniciarCarrera() {
        Ruta ruta = new Ruta("Quillota", "Viña del Mar");

        VehiculoCarrera vehiculo1 = new VehiculoCarrera("Vehículo 1", ruta);
        VehiculoCarrera vehiculo2 = new VehiculoCarrera("Vehículo 2", ruta);
        VehiculoCarrera vehiculo3 = new VehiculoCarrera("Vehículo 3", ruta);

        vehiculo1.start();
        vehiculo2.start();
        vehiculo3.start();

        esperarLlegada(vehiculo1);
        esperarLlegada(vehiculo2);
        esperarLlegada(vehiculo3);

        System.out.println("Todos los vehiculos llegaron a la meta");
    }

    private void esperarLlegada(Thread vehiculo) {
        try {
            vehiculo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido mientras esperaba");
            Thread.currentThread().interrupt();
        }
    }
}
package com.mycompany.ejercicio1paralela;

public class SensorTemperatura extends Thread {
    private final int idSensor;
    private final GeneradorTemperatura generador;

    public SensorTemperatura(int idSensor, GeneradorTemperatura generador) {
        this.idSensor = idSensor;
        this.generador = generador;
    }

    @Override
    public void run() {
        boolean activo = true;

        while (activo) {
            int temperatura = generador.generarTemperatura();

            System.out.println("Sensor " + idSensor + " registra: " + temperatura + "°C");

            if (temperatura > 100) {
                System.out.println(">>> Sensor " + idSensor
                        + " detenido por sobrecalentamiento: " + temperatura + "°C");
                activo = false;
            } else {
                esperarUnInstante();
            }
        }
    }

    private void esperarUnInstante() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sensor " + idSensor + " fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
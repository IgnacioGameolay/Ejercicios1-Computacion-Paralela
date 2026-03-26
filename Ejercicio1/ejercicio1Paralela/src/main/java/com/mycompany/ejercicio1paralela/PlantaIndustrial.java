package com.mycompany.ejercicio1paralela;

public class PlantaIndustrial {
    private static final int CANTIDAD_SENSORES = 10;

    public void iniciarMonitoreo() {
        for (int i = 1; i <= CANTIDAD_SENSORES; i++) {
            GeneradorTemperatura generador = new GeneradorTemperatura();
            SensorTemperatura sensor = new SensorTemperatura(i, generador);
            sensor.start();
        }
    }
}
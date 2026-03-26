package com.mycompany.ejercicio1paralela;
import java.util.Random;

public class GeneradorTemperatura {
    private final Random random;

    public GeneradorTemperatura() {
        this.random = new Random();
    }

    public int generarTemperatura() {
        // Rango de ejemplo: 50 a 120 °C
        return random.nextInt(71) + 50;
    }
}
package Ejercicio6;

public class Operador implements Runnable {
    private final String nombre;
    private final Recurso primero;
    private final Recurso segundo;

    public Operador(String nombre, Recurso primero, Recurso segundo) {
        this.nombre = nombre;
        this.primero = primero;
        this.segundo = segundo;
    }

    @Override
    public void run() {
        try {
            primero.adquirir();
            try {
                System.out.printf("%s adquirió %s%n", nombre, primero.getNombre());
                Thread.sleep(100);
                System.out.printf("%s espera %s...%n", nombre, segundo.getNombre());
                segundo.adquirir();
                try {
                    System.out.printf("%s completó la tarea. ✅%n", nombre);
                } finally { segundo.liberar(); }
            } finally { primero.liberar(); }
        } catch (InterruptedException e) {
            System.out.printf("%s interrumpido — liberando recursos.%n", nombre);
            primero.liberar();
            segundo.liberar();
        }
    }
}
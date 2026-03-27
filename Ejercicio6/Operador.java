package Ejercicio6;

public class Operador implements Runnable { // Operador
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
        synchronized (primero) { // Primero se consigue x recurso
            System.out.printf("%s ha conseguido %s%n", nombre, primero.getNombre());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { // Si no se consigue..
                Thread.currentThread().interrupt();
                System.out.printf("%s ha sido interrumpido, se procede a liberar recursos..%n", nombre);
                return;
            }
            System.out.printf("%s está esperando %s...%n", nombre, segundo.getNombre());
            synchronized (segundo) { // Se consigue segundo recurso, y se asume tarea completa
                System.out.printf("%s ha completado la tarea.%n", nombre);
            }
        }
    }
}
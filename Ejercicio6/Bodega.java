package Ejercicio6;

public class Bodega {
    public static void main(String[] args) throws InterruptedException {
        Recurso grua    = new Recurso("Grúa");
        Recurso escaner = new Recurso("Escáner");

        // Deadlock
        System.out.println("=== CON DEADLOCK ===");
        Thread tA = new Thread(new Operador("Operador A", grua, escaner));
        Thread tB = new Thread(new Operador("Operador B", escaner, grua));
        tA.start(); tB.start();

        Thread.sleep(500);
        tA.interrupt(); tB.interrupt();

        // Reset
        tA.join(1000);  tB.join(1000);
        System.out.println(">>> Deadlock detectado.\n");

        // No deadlock
        System.out.println("=== SIN DEADLOCK ===");
        Thread tC = new Thread(new Operador("Operador A", grua, escaner));
        Thread tD = new Thread(new Operador("Operador B", grua, escaner));
        tC.start(); tD.start();
        tC.join();  tD.join();
        System.out.println(">>> Ambos completaron su tarea. ✅");
    }
}
package Ejercicio6;

public class Bodega {
    public static void main(String[] args) throws InterruptedException {
        Recurso grua    = new Recurso("Grúa");
        Recurso escaner = new Recurso("Escáner");

        // Deadlock 
        System.out.println("=== Con Deadlock ===");
        Thread tA = new Thread(new Operador("Operador A", grua, escaner));
        Thread tB = new Thread(new Operador("Operador B", escaner, grua));
        
        tA.start(); tB.start();

        tA.join(1000);
        tB.join(1000);

        if (tA.isAlive() || tB.isAlive()) { // Si siguen corriendo, isAlive() verifica y pasan a ser interrumpidos
            System.out.println("Se ha detectado Deadlock.\n");
            tA.interrupt();
            tB.interrupt();
        }

        // Se reutilizan los mismos recursos para segundo ejemplo
        grua    = new Recurso("Grúa");
        escaner = new Recurso("Escáner");

        // Sin deadlock
        System.out.println("=== Sin Deadlock ===");
        Thread tC = new Thread(new Operador("Operador A", grua, escaner));
        Thread tD = new Thread(new Operador("Operador B", grua, escaner));
        tC.start(); tD.start();
        tC.join();  tD.join();
        System.out.println("Los operadores han completado sus tareas.");
    }
}
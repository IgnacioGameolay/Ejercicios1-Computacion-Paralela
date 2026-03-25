package Ejercicio6;

import java.util.concurrent.locks.ReentrantLock;

public class Recurso {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public Recurso(String nombre) {
        this.nombre = nombre;
    }

    public void adquirir() throws InterruptedException {
        lock.lockInterruptibly();
    }

    public void liberar() {
        if (lock.isHeldByCurrentThread()) lock.unlock();
    }

    public String getNombre() { return nombre; }
}
package com.mycompany.ejercicio3;
public class EjecutorTareas {

    public void ejecutarConRun() {
        System.out.println("ejecutar con funcion run()");

        TareaProcesamiento tarea1 = new TareaProcesamiento("Tarea 1");
        TareaProcesamiento tarea2 = new TareaProcesamiento("Tarea 2");
        TareaProcesamiento tarea3 = new TareaProcesamiento("Tarea 3");

        tarea1.run();
        tarea2.run();
        tarea3.run();

        System.out.println("fin de funcion run()\n");
    }

    public void ejecutarConStart() {
        System.out.println("ejecutar con funcion start() ");

        TareaProcesamiento tarea1 = new TareaProcesamiento("Tarea 1");
        TareaProcesamiento tarea2 = new TareaProcesamiento("Tarea 2");
        TareaProcesamiento tarea3 = new TareaProcesamiento("Tarea 3");

        tarea1.start();
        tarea2.start();
        tarea3.start();

        esperarFin(tarea1);
        esperarFin(tarea2);
        esperarFin(tarea3);

        System.out.println("fin de funcion start()");
    }

    private void esperarFin(Thread tarea) {
        try {
            tarea.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
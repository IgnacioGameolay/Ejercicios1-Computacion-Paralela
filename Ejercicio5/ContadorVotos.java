package Ejercicio5;

public class ContadorVotos {
    private int votos = 0;

    public void votarInseguro() {
        int temp = votos;
        Thread.yield();
        votos = temp + 1;
    }

    public synchronized void votarSeguro() {
        int temp = votos;
        Thread.yield();
        votos = temp + 1;
    }

    public void resetVotos() {
        votos = 0;
    }

    public int getVotos() {
        return votos;
    }

    private static void ejecutarVotacion(int nThreads, Runnable accion) throws InterruptedException {
        Thread[] hilos = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            hilos[i] = new Thread(accion);
        }
        for (Thread h : hilos) h.start();
        for (Thread h : hilos) h.join();
    }

    public static void main(String[] args) throws InterruptedException {
        final int nThreads = 100;
        ContadorVotos urna = new ContadorVotos();

        ejecutarVotacion(nThreads, urna::votarInseguro);
        System.out.printf("Votos (ASYNC): %d (esperado: %d)%n%n", urna.getVotos(), nThreads);

        urna.resetVotos();
        ejecutarVotacion(nThreads, urna::votarSeguro);
        System.out.printf("Votos (SYNC): %d (esperado: %d)%n", urna.getVotos(), nThreads);
    }
}
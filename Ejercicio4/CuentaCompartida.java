package Ejercicio4;

public class CuentaCompartida {
    private static CuentaCompartida instancia;
    private int saldoCompartido;

    private CuentaCompartida(int saldoInicial) {
        this.saldoCompartido = saldoInicial;
    }

    public static CuentaCompartida getInstance(int saldoInicial) {
        if (instancia == null) {
            instancia = new CuentaCompartida(saldoInicial);
        }
        return instancia;
    }

    public static CuentaCompartida getInstance() {
        return instancia;
    }

    public void retirarInseguro(String nombre, int pago) {
        System.out.println("Retirando dinero...");
        if (saldoCompartido >= pago) {
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            saldoCompartido -= pago;
            System.out.printf("[ASYNC] %s retiró %d | Saldo restante: %d%n", nombre, pago, saldoCompartido);
        } else {
            System.out.printf("[ASYNC] %s NO pudo retirar %d | Saldo insuficiente: %d%n", nombre, pago, saldoCompartido);
        }
    }

    public synchronized void retirarSeguro(String nombre, int pago) {
        System.out.println("Retirando dinero...");
        if (saldoCompartido >= pago) {
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            saldoCompartido -= pago;
            System.out.printf("[SYNC] %s retiró %d | Saldo restante: %d%n", nombre, pago, saldoCompartido);
        } else {
            System.out.printf("[SYNC] %s NO pudo retirar %d | Saldo insuficiente: %d%n", nombre, pago, saldoCompartido);
        }
    }

    public int getSaldoCompartido() {
        return saldoCompartido;
    }

    public static void main(String[] args) throws InterruptedException {
        CuentaCompartida.getInstance(500000);

        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();

        System.out.println("=== ASYNC ===");
        Thread h1 = new Thread(() -> c1.pagarArriendoInseguro("Habitante 1", 350000));
        Thread h2 = new Thread(() -> c2.pagarArriendoInseguro("Habitante 2", 350000));
        h1.start(); h2.start();
        h1.join();  h2.join();
        System.out.printf("Saldo total: %d%n", CuentaCompartida.getInstance().getSaldoCompartido());

        instancia = null;
        CuentaCompartida.getInstance(500000);

        System.out.println("\n=== SYNC ===");
        Cliente c3 = new Cliente();
        Cliente c4 = new Cliente();
        Thread h3 = new Thread(() -> c3.pagarArriendoSeguro("Habitante 1", 350000));
        Thread h4 = new Thread(() -> c4.pagarArriendoSeguro("Habitante 2", 350000));
        h3.start(); h4.start();
        h3.join();  h4.join();

        System.out.printf("Saldo total: %d%n", CuentaCompartida.getInstance().getSaldoCompartido());
    }
}
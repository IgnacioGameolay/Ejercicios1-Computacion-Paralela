package Ejercicio4;

public class Cliente {
    private CuentaCompartida cuenta;

    public Cliente() {
        this.cuenta = CuentaCompartida.getInstance();
    }

    public void pagarArriendoInseguro(String nombre, int pago) {
        cuenta.retirarInseguro(nombre, pago);
    }

    public void pagarArriendoSeguro(String nombre, int pago) {
        cuenta.retirarSeguro(nombre, pago);
    }
}
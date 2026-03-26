package com.mycompany.ejercicio7;

/**
 * Clase creada para facilitar la revisión del ejercicio.
 * Levanta el servidor y multiples clientes en paralelo.
**/
public class Ejercicio7 {
    public static void main(String[] args) {
        // 1. Iniciar el Servidor en un hilo independiente para no bloquear el Main
        Thread hiloServidor = new Thread(() -> {
            System.out.println("Iniciando una instancia del Servidor...");
            Servidor.main(null);
        });
        hiloServidor.setDaemon(true); // Este metodo nos cierra el servidor una vez el main termina, sino se nos queda encendido y el hilo corriendo
        hiloServidor.start();

        // 2. Esperamos 1s para asegurar que el ServerSocket se haya terminado de klevantar.
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        System.out.println("Inicializando multiples clientes simultaneos (Simulacion multi-ciudad)...");

        // 3. Lanzar Cliente 1 (Simulando Santiago)
        lanzarClienteAutomatizado("Santiago: Hola desde SANTIAGOOO");

        // 4. Lanzar Cliente 2 (Simulando Quilpue)
        lanzarClienteAutomatizado("Quilpue: Hola desde QUILLOTAAA");
        
        System.out.println("Todos los hilos de respuesta activos Y listos.");
    }

    /**
     * Metodo de automatizacion para no requerir input manual y facilitar la revision.
     */
    private static void lanzarClienteAutomatizado(String mensaje) {
        new Thread(() -> {
            try {
                // Logica dle cliente
                java.net.Socket s = new java.net.Socket("localhost", 5000);
                java.io.PrintWriter out = new java.io.PrintWriter(s.getOutputStream(), true);
                java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(s.getInputStream())
                );

                out.println(mensaje);
                System.out.println("Mensaje del Cliente enviado: " + mensaje);
                System.out.println("Respuesta del servidor (eco invertido): " + in.readLine());
                
                s.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }).start();
    }
}
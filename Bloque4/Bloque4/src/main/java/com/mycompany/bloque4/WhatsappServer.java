package com.mycompany.bloque4;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

//Servidor de Mensajería de Grupo
public class WhatsappServer {

    // Lista para gestuibar a los miembros del grupo
    private static List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("=================================================");
        System.out.println("Servidor de grupo iniciado...");
        System.out.println("=================================================");
        while (true) {
            
            Socket socket = servidor.accept();
            
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            
            synchronized (clientes) {
                clientes.add(salida); // Agregar nuevo miembro al grupo 
            }
            
            
            // Tarea: Crear un hilo para escuchar los mensajes de este cliente
            // y reenviarlos a TODOS los demás miembros de la lista 'clientes'
            salida.println("connect_ack");
            new Thread(new ManejarMensajes(socket, salida)).start();
        }
    }

    // FUnción dedicada a "Implementar la Difusión" y "Manejo de Fallos".
    static class ManejarMensajes implements Runnable {
        private final Socket socket;
        private final PrintWriter flujoOrigen;

        public ManejarMensajes(Socket socket, PrintWriter flujoOrigen) {
            this.socket = socket;
            this.flujoOrigen = flujoOrigen;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String mensaje;
                
                while ((mensaje = in.readLine()) != null) {
                    System.out.println("Procesando el Broadcast: " + mensaje);

                    // Implementar la Difusión (Broadcast) y Manejo de Fallos (Instrucciones 2 y 3 del pdf)
                    synchronized (clientes) {
                        Iterator<PrintWriter> iterador = clientes.iterator();
                        
                        while (iterador.hasNext()) {
                            PrintWriter receptor = iterador.next();
                            
                            // Reenviar a los demás miembros
                            if (receptor != flujoOrigen) {
                                receptor.println("propagate_msg: " + mensaje);
                                
                                // Manejo de posibles desconexiones
                                if (receptor.checkError()) {
                                    iterador.remove();
                                    System.out.println("Receptor desconectado. Eliminado de la lista activa de miembros.");
                                }
                            }
                        }
                    }
                    
                    // Confirmar al emisor que el broadcast ya terminó
                    flujoOrigen.println("broadcast_done_ack");
                }
            } catch (IOException e) {
                System.out.println("Desconexión de cliente detectada en el flujo de entrada.");
            } finally {
                // Limpieza del cliente actual si abandona el ciclo de mensajería
                synchronized (clientes) {
                    clientes.remove(flujoOrigen);
                }
                try { socket.close(); } catch (IOException e) {}
            }
        }
    }
}

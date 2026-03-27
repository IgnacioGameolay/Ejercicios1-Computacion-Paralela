package com.mycompany.bloque4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WhatsappClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            // Hilo asíncrono para la escucha de mensajes
            Thread hiloEscucha = new Thread(() -> {
                try {
                    String respuestaServidor;
                    while ((respuestaServidor = entrada.readLine()) != null) {
                        System.out.println("\nServidor: " + respuestaServidor);
                        System.out.print("Escribir mensaje: ");
                    }
                } catch (IOException e) {
                    System.out.println("Conexión finalizada.");
                }
            });
            hiloEscucha.setDaemon(true);
            hiloEscucha.start();

            // Bucle principal de envío
            System.out.println("Conectando al grupo...");
            System.out.print("Escribir mensaje: ");
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("salir")) break;
                
                salida.println(input);
            }

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
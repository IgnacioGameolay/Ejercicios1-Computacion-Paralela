package com.mycompany.ejercicio7;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;
        
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de Eco Multihilo iniciado en el puerto " + puerto);
            
            while (true) {
                // El hilo principal solo se bloquea esperando nuevas conexiones
                Socket socketCliente = servidor.accept();
                System.out.println("Nueva conexion desde: " + socketCliente.getInetAddress());
                
                // Delegar a un nuevo hilo
                Thread hiloCliente = new Thread(new ManejadorEco(socketCliente));
                hiloCliente.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
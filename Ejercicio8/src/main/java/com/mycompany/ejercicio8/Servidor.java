package com.mycompany.ejercicio8;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Servidor{
    public static void main(String[] args) {
        int puerto = 5000;
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor Municipal levantado en puerto: " + puerto);
            
            while (true) {
                Socket socketCliente = servidor.accept();
                // Asignamos a un hilo para manejar el login
                new Thread(new ManejadorLogin(socketCliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Eror de Servidor: " + e.getMessage());
        }
    }
}
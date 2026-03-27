package com.mycompany.ejercicio8;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class ManejadorLogin implements Runnable {
    private final Socket socket;
    private static final String CREDENCIALES_VALIDAS = "estudiante:pucv2026";

    public ManejadorLogin(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)
        ) {
            String textoCredenciales = entrada.readLine();
            
            if (formatoValido(textoCredenciales)) {
                if (textoCredenciales.equals(CREDENCIALES_VALIDAS)) {
                    salida.println("OK");
                } else {
                    salida.println("ERROR");
                }
            } else {
                salida.println("ERROR: Formato incorrecto (usuario:password)");
            }

        } catch (SocketException e) {
            // Manejamos la desconexion
            System.err.println("Aviso: Un ciudadano perdio la conexion.");
        } catch (IOException e) {
            // Manejamos cualquier otro error de Input / Output
            System.err.println("Error de I/O en la sesion: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private boolean formatoValido(String textoCredenciales) {
        return textoCredenciales != null && textoCredenciales.contains(":");
    }
}
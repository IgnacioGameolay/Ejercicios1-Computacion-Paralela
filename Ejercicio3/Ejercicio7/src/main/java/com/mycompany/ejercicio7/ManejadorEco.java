package com.mycompany.ejercicio7;

import java.io.*;
import java.net.Socket;

public class ManejadorEco implements Runnable { // Implementamos Runnabe para comprometer a la clase a ser multi-hilo
    private final Socket socket;

    public ManejadorEco(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String mensaje;
            // El servidor escucha hasta que el cliente cierra la conexión con este
            while ((mensaje = entrada.readLine()) != null) {
                // Logica de eco invertido (se invierte la cadena y se envia por salida)
                String ecoInvertido = new StringBuilder(mensaje).reverse().toString();
                salida.println(ecoInvertido);
            }
        } catch (IOException e) {
            System.err.println("Conexión finalizada con el cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
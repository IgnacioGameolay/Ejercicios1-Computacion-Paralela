package com.mycompany.ejercicio9;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;
        File archivo = new File("Manual_Procedimientos.txt");

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor activo en puerto " + puerto);

            while (true) {
                Socket sucursal = servidor.accept();
                System.out.println("Transfiriendo manual a: " + sucursal.getInetAddress());
                
                // Delegamos a un hilo para no bloquear otras sucursales
                new Thread(() -> enviarArchivo(sucursal, archivo)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor de archivos: " + e.getMessage());
        }
    }

    private static void enviarArchivo(Socket socket, File archivo) {
        try (
            BufferedReader lectorDoc = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8));
            PrintWriter escritorDoc = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)
        ) {
            String linea;
            while ((linea = lectorDoc.readLine()) != null) {
                escritorDoc.println(linea);
            }
            escritorDoc.println("ManualEOF");
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: Manual no encontrado en el servidor.");
        } catch (IOException e) {
            System.err.println("Error durante la transferencia: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }
}
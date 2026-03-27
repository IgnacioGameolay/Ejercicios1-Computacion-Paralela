package com.mycompany.ejercicio9;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Cliente {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
            
            System.out.println("=================================================");
            System.out.println("==== Recibiendo Manual de Procedimientos ===");
            System.out.println("=================================================");

            //Empezamos a leer el archivo
            String linea;
            while ((linea = entrada.readLine()) != null) {
                if ("ManualEOF".equals(linea)) break;
                
                System.out.println("Documento: " + linea);
            }

            System.out.println("=================================================");
            System.out.println("Transferencia finalizada con éxito");
            System.out.println("=================================================");
            
        } catch (IOException e) {
            System.err.println("Error de conexión con la central: " + e.getMessage());
        }
    }
}
package com.mycompany.ejercicio8;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente{
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             Scanner scanner = new Scanner(System.in, "UTF-8")) {
            
            System.out.println("=== Sistema Municipal ===");
            System.out.print("Ingrese credenciales (usuario:password): ");
            String credenciales = scanner.nextLine();
            
            salida.println(credenciales);
            String respuesta = entrada.readLine();
            
            System.out.println("Respuesta del servidor: " + respuesta);
            System.out.println("===============");
            
        } catch (IOException e) {
            System.err.println("Error: No se pudo conectar con el municipio.");
        }
    }
}
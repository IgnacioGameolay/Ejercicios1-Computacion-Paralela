package com.mycompany.ejercicio7;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
                
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Conectado al servidor a enviar el eco... Escriba el mensaje a enviar al servidor:");
            
            while (true) {
                String msg = scanner.nextLine();
                if (msg.equalsIgnoreCase("salir")) break;
                
                salida.println(msg);
                System.out.println("Respuesta del servidor (Eco invertido): " + entrada.readLine());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.rpgproyect;
import java.util.Scanner; // siempre al inicio del archivo
/**
 *
 * @author kazukkuwo
 */
public class RPGproyect {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
int opcion;
do {
System.out.println("\n=== MENÚ ===");
System.out.println("1. Opción A");
System.out.println("2. Opción B");
System.out.println("0. Salir");
System.out.print("Elige una opción: ");
opcion = Integer.parseInt(sc.nextLine());
switch (opcion) {
case 1: System.out.println("Elegiste A"); break;
case 2: System.out.println("Elegiste B"); break;
case 0: System.out.println("Hasta luego"); break;
default: System.out.println("Opción inválida");
}
} while (opcion != 0);
sc.close();
    }
}

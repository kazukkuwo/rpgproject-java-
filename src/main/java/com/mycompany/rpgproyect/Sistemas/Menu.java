
package com.mycompany.rpgproyect.Sistemas;
import com.mycompany.rpgproyect.objetos.Arma;
import com.mycompany.rpgproyect.objetos.Armadura;
import com.mycompany.rpgproyect.Jugador;
import com.mycompany.rpgproyect.objetos.Miscelanea;
import com.mycompany.rpgproyect.Mision;
import com.mycompany.rpgproyect.pjs.Personajes;
import com.mycompany.rpgproyect.objetos.Pocion;
import com.mycompany.rpgproyect.enums.Rareza;
import com.mycompany.rpgproyect.RegistroMision;
import com.mycompany.rpgproyect.inventario.Item;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author kazukkuwo
 */
public class Menu {
private Scanner sc;
private Sistema sistema;

public Menu(Sistema sistema) {
        this.sistema = sistema;
        this.sc      = new Scanner(System.in);
    }

   

    public void menuPrincipal() {
        int op;
        do {
            System.out.println("\n------------------------------");
            System.out.println("        SISTEMA RPG");
            System.out.println("-------------------------------");
            System.out.println("  1. Registrarse");
            System.out.println("  2. Iniciar sesion");
            System.out.println("  0. Salir");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> flujoRegistro();
                case 2 -> flujoLogin();
                case 0 -> salir();
                default -> System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

  

    private void flujoRegistro() {
        System.out.println("\n--- REGISTRO ---");
        System.out.print("Apodo (sin espacios): ");         String apodo    = sc.nextLine().trim();
        System.out.print("Nombre: ");                        String nombre   = sc.nextLine().trim();
        System.out.print("Apellido: ");                      String apellido = sc.nextLine().trim();
        System.out.print("Correo: ");                        String correo   = sc.nextLine().trim();
        System.out.print("Fecha nacimiento (DD/MM/AAAA): "); String fecha    = sc.nextLine().trim();

        String resultado = sistema.registrarJugador(apodo, nombre, apellido, correo, fecha);
        if (resultado.startsWith("ERROR")) {
            System.out.println("mal " + resultado);
        } else {
            System.out.println("bien Cuenta creada. Tu clave es: " + resultado.replace("OK:", ""));
        }
    }

    

    private void flujoLogin() {
        System.out.println("\n--- INICIO DE SESION ---");
        System.out.print("Correo: "); String correo = sc.nextLine().trim();
        System.out.print("Clave: ");  String clave  = sc.nextLine().trim();

        if (sistema.iniciarSesion(correo, clave)) {
            System.out.println("bien Bienvenido, " + sistema.getJugadorActual().getApodo() + "!");
            flujoSeleccionPersonaje();
        } else {
            System.out.println("mal Correo o clave incorrectos.");
        }
    }

   
    private void flujoSeleccionPersonaje() {
        Jugador j = sistema.getJugadorActual();
        ArrayList<Personajes> lista = j.getPersonajes();

        if (lista.isEmpty()) {
            System.out.println("\nNo tienes personajes. Crea uno primero.");
            crearPersonaje();
            lista = j.getPersonajes();
            if (lista.isEmpty()) { sistema.cerrarSesion(); return; }
        }

        System.out.println("\n--- ¿Con quien vas a jugar? ---");
        for (int i = 0; i < lista.size(); i++)
            System.out.println("  " + (i+1) + ". " + lista.get(i).resumen());

        System.out.print("Elige personaje: ");
        int idx = leerInt() - 1;
        if (idx < 0 || idx >= lista.size()) {
            System.out.println("Opcion invalida. Sesion cerrada.");
            sistema.cerrarSesion();
            return;
        }

        sistema.seleccionarPersonaje(idx);
        System.out.println("\n¡Jugando con " + sistema.getPersonajeActivo().getNombre() + "!");
        menuJugador();
    }

    

    private void menuJugador() {
        int op;
        do {
            Personajes p = sistema.getPersonajeActivo();
            System.out.println("\n--------------------------");
            System.out.println("  " + p.getNombre() + " [" + p.getNombreClase() +
                    "] Nv." + p.getNivel() + " | Oro:" + p.getOro());
            System.out.println("---------------------------------");
            System.out.println("  1. Ver perfil del jugador");
            System.out.println("  2. Gestion de personajes");
            System.out.println("  3. Inventario");
            System.out.println("  4. Misiones");
            System.out.println("  5. Cambiar personaje");
            System.out.println("  0. Cerrar sesion");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> verPerfil();
                case 2 -> menuPersonajes();
                case 3 -> menuInventario();
                case 4 -> menuMisiones();
                case 5 -> {
                    flujoSeleccionPersonaje();
                    return;  
                }
                case 0 -> {
                    sistema.cerrarSesion();
                    System.out.println("Sesion cerrada. Datos guardados.");
                }
                default -> System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    

    private void verPerfil() {
        Jugador j = sistema.getJugadorActual();
        System.out.println("\n--- PERFIL ---");
        System.out.println("  Apodo:      " + j.getApodo());
        System.out.println("  Nombre:     " + j.getNombre() + " " + j.getApellido());
        System.out.println("  Correo:     " + j.getCorreo());
        System.out.println("  Nacimiento: " + j.getFechaNacimeinto());
        System.out.println("  Personajes: " + j.getTotalPersonajes() + "/5 " +
                "(activos:" + j.getPersonajes().size() + " bodega:" + j.getBodega().size() + ")");
        System.out.println("\n  -- Personaje actual --");
        System.out.println(sistema.getPersonajeActivo().Ficha());
    }

    // ─── MENÚ PERSONAJES ──────────────────────────────────────────────────────

    private void menuPersonajes() {
        int op;
        do {
            System.out.println("\n--- GESTION DE PERSONAJES ---");
            System.out.println("  1. Ver todos mis personajes");
            System.out.println("  2. Crear personaje");
            System.out.println("  3. Ver ficha del personaje actual");
            System.out.println("  4. Enviar personaje a la bodega");
            System.out.println("  5. Ver bodega");
            System.out.println("  6. Ordenar personajes");
            System.out.println("  0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1: listarPersonajes(sistema.getJugadorActual().getPersonajes()); break;
                case 2: crearPersonaje();     break;
                case 3: System.out.println(sistema.getPersonajeActivo().Ficha()); break;
                case 4: descartarPersonaje(); break;
                case 5: bodegaPersonajes();   break;
                case 6: ordenarPersonajes();  break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    private void listarPersonajes(ArrayList<Personajes> lista) {
        if (lista.isEmpty()) { System.out.println("  (Sin personajes)"); return; }
        Personajes actual = sistema.getPersonajeActivo();
        for (int i = 0; i < lista.size(); i++) {
            String marca = lista.get(i) == actual ? " ◄ jugando" : "";
            System.out.println("  " + (i+1) + ". " + lista.get(i).resumen() + marca);
        }
    }

    private void crearPersonaje() {
        Jugador j = sistema.getJugadorActual();
        if (j.getTotalPersonajes() >= 5) {
            System.out.println("Limite de 5 personajes alcanzado (activos + bodega).");
            return;
        }
        System.out.println("\n-- Crear Personaje --");
        System.out.print("Nombre (sin espacios, max 20 chars): ");
        String nombre = sc.nextLine().trim();
        System.out.println("Clase: 1.Caballero  2.Arcanista  3.Explorador");
        System.out.print("Clase: ");
        int clase = leerInt();
        System.out.println(sistema.crearPersonaje(nombre, clase));
    }

    private void descartarPersonaje() {
        ArrayList<Personajes> lista = sistema.getJugadorActual().getPersonajes();
        listarPersonajes(lista);
        if (lista.size() <= 1) {
            System.out.println("Necesitas al menos 1 personaje activo.");
            return;
        }
        System.out.print("Numero de personaje a enviar a bodega: ");
        int idx = leerInt() - 1;
        if (lista.get(idx) == sistema.getPersonajeActivo()) {
            System.out.println("No puedes enviar a la bodega el personaje con el que estas jugando. Cambia primero.");
            return;
        }
        System.out.println(sistema.descartarPersonaje(idx));
    }

    private void bodegaPersonajes() {
        ArrayList<Personajes> bodega = sistema.getJugadorActual().getBodega();
        System.out.println("\n-- Bodega de Personajes --");
        if (bodega.isEmpty()) { System.out.println("  (Vacia)"); return; }
        for (int i = 0; i < bodega.size(); i++)
            System.out.println("  " + (i+1) + ". " + bodega.get(i).resumen());
        System.out.println("  1.Recuperar  2.Eliminar definitivamente  0.Volver");
        System.out.print("Opcion: ");
        int op = leerInt(); if (op == 0) return;
        System.out.print("Numero de personaje: ");
        int idx = leerInt() - 1;
        if (op == 1)      System.out.println(sistema.recuperarPersonaje(idx));
        else if (op == 2) System.out.println(sistema.eliminarPersonajeDefinitivamente(idx));
    }

    private void ordenarPersonajes() {
        System.out.println("Ordenar por: 1.Nivel  2.XP  3.Fecha creacion");
        System.out.print("Opcion: ");
        int op = leerInt();
        Jugador j = sistema.getJugadorActual();
        if (op == 1)      j.ordenarPorNivel();
        else if (op == 2) j.ordenarPorXP();
        else if (op == 3) j.ordenarPorFechaCreacion();
        listarPersonajes(j.getPersonajes());
    }

    // ─── MENÚ INVENTARIO ──────────────────────────────────────────────────────

    private void menuInventario() {
        Personajes p = sistema.getPersonajeActivo();
        int op;
        do {
            System.out.println("\n-- Inventario de " + p.getNombre() +
                    " | " + p.getInventario().resumen() + " --");
            System.out.println("  1. Ver items");
            System.out.println("  2. Agregar item");
            System.out.println("  3. Usar item");
            System.out.println("  4. Equipar item");
            System.out.println("  5. Descartar item a bodega");
            System.out.println("  6. Ver bodega de items");
            System.out.println("  0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1: verItems(p);        break;
                case 2: agregarItem();      break;
                case 3: usarItem(p);        break;
                case 4: equiparItem(p);     break;
                case 5: descartarItem(p);   break;
                case 6: bodegaItems(p);     break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    private void verItems(Personajes p) {
        ArrayList<Item> items = p.getInventario().getItems();
        if (items.isEmpty()) { System.out.println("  (Inventario vacio)"); return; }
        for (int i = 0; i < items.size(); i++)
            System.out.println("  " + (i+1) + ". " + items.get(i));
    }

    private void agregarItem() {
        System.out.println("\n-- Agregar Item --");
        System.out.print("Nombre: ");      String nombre = sc.nextLine().trim();
        System.out.print("Descripcion: "); String desc   = sc.nextLine().trim();
        System.out.println("Tipo: 1.Arma  2.Armadura  3.Pocion  4.Miscelanea");
        int tipo = leerInt();
        System.out.println("Rareza: 1.Comun  2.Raro  3.Epico  4.Legendario");
        int rar = leerInt();
        Rareza rareza = Rareza.values()[Math.max(0, Math.min(3, rar-1))];
        System.out.print("Peso (kg): "); double peso = leerDouble();

        Item item;
        switch (tipo) {
            case 1:
                System.out.print("Daño base: ");           int danio = leerInt();
                System.out.print("Velocidad (1-10): ");    int vel   = leerInt();
                System.out.print("Penalizacion agilidad: ");int pen  = leerInt();
                item = new Arma(nombre, desc, rareza, peso, danio, vel, pen);
                break;
            case 2:
                System.out.print("Bonus defensa: ");          int def  = leerInt();
                System.out.print("Penalizacion agilidad: ");  int penA = leerInt();
                item = new Armadura(nombre, desc, rareza, peso, def, penA);
                break;
            case 3:
                System.out.print("Cantidad de efecto: ");  int efecto = leerInt();
                System.out.println("Efecto: 1.Vida  2.Mana  3.Fuerza  4.XP");
                int te = leerInt();
                Pocion.TipoEfecto tipoEf = Pocion.TipoEfecto.values()[Math.max(0,Math.min(3,te-1))];
                item = new Pocion(nombre, desc, rareza, peso, efecto, tipoEf);
                break;
            case 4:
                System.out.print("XP que otorga: "); int xp = leerInt();
                item = new Miscelanea(nombre, desc, rareza, peso, xp);
                break;
            default: System.out.println("Tipo invalido."); return;
        }
        System.out.println(sistema.agregarItem(item));
    }

    private void usarItem(Personajes p) {
        verItems(p);
        if (p.getInventario().getItems().isEmpty()) return;
        System.out.print("Numero de item a usar: ");
        System.out.println(sistema.usarItem(leerInt() - 1));
    }

    private void equiparItem(Personajes p) {
        verItems(p);
        if (p.getInventario().getItems().isEmpty()) return;
        System.out.print("Numero de item a equipar: ");
        System.out.println(sistema.equiparItem(leerInt() - 1));
    }

    private void descartarItem(Personajes p) {
        verItems(p);
        if (p.getInventario().getItems().isEmpty()) return;
        System.out.print("Numero de item a descartar: ");
        System.out.println(sistema.descartarItem(leerInt() - 1));
    }

    private void bodegaItems(Personajes p) {
        ArrayList<Item> bodega = p.getInventario().getBodega();
        System.out.println("\n-- Bodega de Items de " + p.getNombre() + " --");
        if (bodega.isEmpty()) { System.out.println("  (Vacia)"); return; }
        for (int i = 0; i < bodega.size(); i++)
            System.out.println("  " + (i+1) + ". " + bodega.get(i));
        System.out.println("  1.Recuperar  2.Eliminar definitivamente  0.Volver");
        System.out.print("Opcion: ");
        int op = leerInt(); if (op == 0) return;
        System.out.print("Numero de item: ");
        int idx = leerInt() - 1;
        if (op == 1)      System.out.println(sistema.recuperarItemBodega(idx));
        else if (op == 2) System.out.println(sistema.eliminarItemBodega(idx));
    }

    // ─── MENÚ MISIONES ────────────────────────────────────────────────────────

    private void menuMisiones() {
        Personajes p = sistema.getPersonajeActivo();
        int op;
        do {
            System.out.println("\n-- Misiones de " + p.getNombre() +
                    " (Nv." + p.getNivel() + ") --");
            System.out.println("  1. Catalogo completo");
            System.out.println("  2. Misiones disponibles para mi nivel");
            System.out.println("  3. Aceptar mision");
            System.out.println("  4. Completar mision");
            System.out.println("  5. Ver historial");
            System.out.println("  0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1: mostrarCatalogo(sistema.getCatalogo());                      break;
                case 2: mostrarCatalogo(sistema.misionesDisponibles(p.getNivel())); break;
                case 3: aceptarMision();                                             break;
                case 4: completarMision(p);                                          break;
                case 5: verHistorial(p);                                             break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    private void mostrarCatalogo(ArrayList<Mision> lista) {
        if (lista.isEmpty()) { System.out.println("  (Sin misiones)"); return; }
        for (int i = 0; i < lista.size(); i++)
            System.out.println("  " + (i+1) + ". " + lista.get(i));
    }

    private void aceptarMision() {
        mostrarCatalogo(sistema.getCatalogo());
        System.out.print("Numero de mision: ");
        System.out.println(sistema.aceptarMision(leerInt() - 1));
    }

    private void completarMision(Personajes p) {
        ArrayList<Mision> cat = sistema.getCatalogo();
        ArrayList<Integer> enProgreso = new ArrayList<>();
        for (int i = 0; i < cat.size(); i++) {
            if (p.tieneMisionActiva(cat.get(i).getNombre())) {
                enProgreso.add(i);
                System.out.println("  " + enProgreso.size() + ". " + cat.get(i).getNombre());
            }
        }
        if (enProgreso.isEmpty()) { System.out.println("No tienes misiones en progreso."); return; }
        System.out.print("Numero de mision a completar: ");
        int sel = leerInt() - 1;
        if (sel < 0 || sel >= enProgreso.size()) { System.out.println("Invalido."); return; }
        System.out.println(sistema.completarMision(enProgreso.get(sel)));
    }

    private void verHistorial(Personajes p) {
        ArrayList<RegistroMision> hist = p.getHistorialMisiones();
        System.out.println("\n-- Historial de " + p.getNombre() + " --");
        if (hist.isEmpty()) { System.out.println("  (Sin misiones)"); return; }
        for (RegistroMision rm : hist) System.out.println("  " + rm);
    }

    // ─── UTILS ────────────────────────────────────────────────────────────────

    private int leerInt() {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Ingresa un numero valido."); return -1; }
    }

    private double leerDouble() {
        try { return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }

    private void salir() {
        sistema.guardar();
        System.out.println("Datos guardados. ¡Hasta luego!");
        sc.close();
    }
}

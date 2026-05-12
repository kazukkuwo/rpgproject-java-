
package com.mycompany.rpgproyect;



public class RPGproyect {
    public static void main(String[]args){
        Sistema sistema = new Sistema();
        Menu menu = new Menu(sistema);
        menu.menuPrincipal();
    }


}

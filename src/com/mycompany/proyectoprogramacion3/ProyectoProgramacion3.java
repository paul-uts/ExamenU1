/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoprogramacion3;


import Menu.Principal;
import Persistencia.*;


/**
 *
 * @author David  Alejandro Tirado Luna
 */
public class ProyectoProgramacion3 {

    public static void main(String[] args) {

        IPersistenciaFachada persistencia = new PersistenciaFachada();
        Principal menuPrincipal = new Principal();
        menuPrincipal.setVisible(true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Enrique Osuna
 */
public class Especialidad {
    
    private int id;
    private String nombre;

    public Especialidad(int id, String nombre) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID de la especialidad debe ser positivo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre de la especialidad no puede estar vacío.");
        }
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public static Especialidad fromString(String data) throws Exception {
        String[] parts = data.split(",");
        if (parts.length != 2) {  
            throw new Exception("Formato incorrecto");
        }

        int id = Integer.parseInt(parts[0]); 
        String nombre = parts[1];                

        return new Especialidad(id, nombre);     
    }

    


  
            @Override
    public String toString() {
        return id + "," + nombre;
    }
}

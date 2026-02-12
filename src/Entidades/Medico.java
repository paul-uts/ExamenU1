/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Persistencia.PersistenciaEspecialidades2;

/**
 *
 * @author Enrique Osuna
 */
public class Medico {
    
    private int id;
    private String nombre;
    private Especialidad especialidad;

    public Medico(int id, String nombre, Especialidad especialidad) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID del médico debe ser positivo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre del médico no puede estar vacío.");
        }
        if (especialidad == null) {
            throw new Exception("La especialidad no puede ser nula.");
        }
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Medico() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
  
public static Medico fromString(String data, PersistenciaEspecialidades2 persistenciaEspecialidades) throws Exception {
        String[] parts = data.split(",");
        if (parts.length != 3) { 
            throw new Exception("Formato incorrecto");
        }

        int id = Integer.parseInt(parts[0]);       
        String nombre = parts[1];               
        int especialidadId = Integer.parseInt(parts[2]); 

       
        Especialidad especialidad = persistenciaEspecialidades.obtenerEspecialidadPorId(especialidadId);
        if (especialidad == null) {
            throw new Exception("Especialidad no encontrada para el ID " + especialidadId);
        }

        
        return new Medico(id, nombre, especialidad);
    }
    
    @Override
    public String toString() {
        return id + "," + nombre + "," + especialidad.getId();
    }

  public static Medico fromString(String data) throws Exception {
    String[] parts = data.split(",");
    if (parts.length != 3) {
        throw new Exception("Formato incorrecto");
    }

    int id = Integer.parseInt(parts[0]);
    String nombre = parts[1];
    int especialidadId = Integer.parseInt(parts[2]);

    PersistenciaEspecialidades2 persistenciaEspecialidades = new PersistenciaEspecialidades2();
    Especialidad especialidad = persistenciaEspecialidades.obtenerEspecialidadPorId(especialidadId);
    if (especialidad == null) {
        throw new Exception("Especialidad no encontrada para el ID " + especialidadId);
    }

    return new Medico(id, nombre, especialidad);
}
    
}

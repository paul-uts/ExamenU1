/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.List;

/**
 *
 * @author Enrique Osuna
 */
public class Consulta {
    
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String fecha; // Se maneja como String (yyyy-MM-dd) para simplificar

    public Consulta(int id, Paciente paciente, Medico medico, String fecha) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID de la consulta debe ser positivo.");
        }
        if (paciente == null) {
            throw new Exception("El paciente no puede ser nulo.");
        }
        if (medico == null) {
            throw new Exception("El médico no puede ser nulo.");
        }
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new Exception("La fecha de la consulta no puede estar vacía.");
        }

        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getFecha() {
        return fecha;
    }
    
        @Override
    public String toString() {
        return id + ";" + paciente.getId() + ";" + medico.getId() + ";" + fecha;
    }

    // Método fromString para convertir una línea de texto en un objeto Consulta
    public static Consulta fromString(String linea, List<Paciente> pacientes, List<Medico> medicos) throws Exception {
        String[] partes = linea.split(";");
        if (partes.length != 4) {
            throw new Exception("Formato de línea inválido para la consulta.");
        }

        int id = Integer.parseInt(partes[0]);
        int pacienteId = Integer.parseInt(partes[1]);
        int medicoId = Integer.parseInt(partes[2]);
        String fecha = partes[3];

        // Buscar paciente y médico en las listas
        Paciente paciente = pacientes.stream()
                                      .filter(p -> p.getId() == pacienteId)
                                      .findFirst()
                                      .orElseThrow(() -> new Exception("Paciente no encontrado con ID " + pacienteId));

        Medico medico = medicos.stream()
                               .filter(m -> m.getId() == medicoId)
                               .findFirst()
                               .orElseThrow(() -> new Exception("Médico no encontrado con ID " + medicoId));

        return new Consulta(id, paciente, medico, fecha);
    }

    
    
    
}


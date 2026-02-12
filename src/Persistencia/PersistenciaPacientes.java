/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.stream.Collectors;

public class PersistenciaPacientes {

    private static final String ARCHIVO_PACIENTES = "pacientes.txt";
    private List<Paciente> pacientes;

    public PersistenciaPacientes() {
        this.pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) throws Exception {

        try {
            Files.write(Paths.get(ARCHIVO_PACIENTES), (paciente.toString() + System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo" + e.getMessage());
        }

    }

    private void guardarListaPacientes(List<Paciente> pacientes) {
        try {
            List<String> lineas = pacientes.stream().map(Paciente::toString).collect(Collectors.toList());
            Files.write(Paths.get(ARCHIVO_PACIENTES), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo" + e.getMessage());
        }
    }

    public void actualizarPaciente(Paciente pacienteActualizado) throws Exception {
        List<Paciente> pacientes = listarPacientes();
        pacientes = pacientes.stream()
                .map(p -> p.getId() == pacienteActualizado.getId() ? pacienteActualizado : p)
                .collect(Collectors.toList());
        guardarListaPacientes(pacientes);
    }

    public void eliminarPaciente(int id) throws Exception {
        List<Paciente> pacientes = listarPacientes();
        pacientes.removeIf(p -> p.getId() == id);
        guardarListaPacientes(pacientes);
    }

    public Paciente obtenerPacientePorId(int id) throws Exception {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new Exception("No se encontró un paciente con ID " + id);
    }

    public List<Paciente> listarPacientes() throws Exception {
        List<Paciente> pacientes = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_PACIENTES));
            for (String linea : lineas) {
                Paciente paciente = Paciente.fromString(linea);
                if (paciente != null) {
                    pacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return pacientes;
    }

    public Paciente buscarPacientePorID(int id) throws Exception {
        return listarPacientes().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        
    }

}

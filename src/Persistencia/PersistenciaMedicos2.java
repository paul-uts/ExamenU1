/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class PersistenciaMedicos2 {

    private static final String ARCHIVO_MEDICOS = "medicos.txt";
    private PersistenciaEspecialidades2 persistenciaEspecialidades;

    public PersistenciaMedicos2() {
        this.persistenciaEspecialidades = new PersistenciaEspecialidades2();
    }

    public void agregarMedico(Medico medico) throws Exception {
        Files.write(Paths.get(ARCHIVO_MEDICOS), (medico.toString() + System.lineSeparator()).getBytes(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private void guardarListaMedicos(List<Medico> medicos) throws IOException {
        List<String> lineas = medicos.stream().map(Medico::toString).collect(Collectors.toList());
        Files.write(Paths.get(ARCHIVO_MEDICOS), lineas);
    }

    public List<Medico> listarMedicos() throws Exception {
        List<Medico> medicos = new ArrayList<>();
        List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_MEDICOS));
        for (String linea : lineas) {
            Medico medico = Medico.fromString(linea, persistenciaEspecialidades);
            medicos.add(medico);
        }
        return medicos;
    }

    public Medico obtenerMedicoPorId(int id) throws Exception {
        return listarMedicos().stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminarMedico(int id) throws Exception {
        List<Medico> medicos = listarMedicos();
        medicos.removeIf(m -> m.getId() == id);
        guardarListaMedicos(medicos);
    }

    public void actualizarMedico(Medico medicoActualizado) throws Exception {
        List<Medico> medicos = listarMedicos();
        medicos = medicos.stream()
                .map(m -> m.getId() == medicoActualizado.getId() ? medicoActualizado : m)
                .collect(Collectors.toList());
        guardarListaMedicos(medicos);
    }
}

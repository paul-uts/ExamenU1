package Persistencia;

import Entidades.Consulta;
import Entidades.Paciente;
import Entidades.Medico;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersistenciaConsultas {

    private static final String ARCHIVO_CONSULTAS = "consultas.txt";
    private PersistenciaMedicos2 persistenciaMedicos = new PersistenciaMedicos2();

    public void crearConsulta(Consulta consulta) throws Exception {
        try {

            List<Consulta> consultas = listarConsultas();
            if (consultas.stream().anyMatch(c -> c.getId() == consulta.getId())) {
                throw new Exception("Ya existe una consulta con el ID " + consulta.getId());
            }

            Files.write(Paths.get(ARCHIVO_CONSULTAS), (consulta.toString() + System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new Exception("Error al escribir en el archivo de consultas: " + e.getMessage());
        }
    }

    public void eliminarConsulta(int id) throws Exception {
        try {
            List<Consulta> consultas = listarConsultas();  // Obtener todas las consultas
            boolean existe = consultas.removeIf(c -> c.getId() == id); // Eliminar consulta si existe
            if (!existe) {
                throw new Exception("Consulta no encontrada con ID " + id);
            }
            // Guardar la lista actualizada en el archivo
            guardarListaConsultas(consultas);
        } catch (IOException e) {
            throw new Exception("Error al eliminar la consulta: " + e.getMessage());
        }
    }

    public Consulta buscarConsulta(int id) throws Exception {
        List<Consulta> consultas = listarConsultas(); // Obtener todas las consultas
        return consultas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new Exception("Consulta no encontrada con ID " + id)); // Retornar la consulta encontrada o lanzar excepción
    }

    // Leer todas las consultas del archivo
    List<Consulta> listarConsultas() throws Exception {
        List<Consulta> consultas = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_CONSULTAS));
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                if (partes.length != 4) {
                    continue; // Validar formato
                }
                int id = Integer.parseInt(partes[0]);
                int pacienteId = Integer.parseInt(partes[1]);
                int medicoId = Integer.parseInt(partes[2]);
                String fecha = partes[3];

                Paciente paciente = new Paciente(pacienteId, "NombrePaciente", 30, "Dirección");
                Medico medico = persistenciaMedicos.obtenerMedicoPorId(medicoId);

                consultas.add(new Consulta(id, paciente, medico, fecha));
            }
        } catch (IOException e) {
            throw new Exception("Error al leer el archivo de consultas: " + e.getMessage());
        }
        return consultas;
    }

    private void guardarListaConsultas(List<Consulta> consultas) throws IOException {
        List<String> lineas = consultas.stream()
                .map(Consulta::toString)
                .collect(Collectors.toList());
        Files.write(Paths.get(ARCHIVO_CONSULTAS), lineas);
    }
}

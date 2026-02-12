package Persistencia;

import Entidades.Especialidad;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersistenciaEspecialidades2 {

    private static final String ARCHIVO_ESPECIALIDADES = "especialidades.txt";
    private List<Especialidad> especialidades;

    public PersistenciaEspecialidades2() {
        try {
            this.especialidades = cargarEspecialidadesDesdeArchivo();
        } catch (Exception e) {
            System.err.println("Error al cargar especialidades: " + e.getMessage());
            this.especialidades = new ArrayList<>();
        }
    }

    public void agregarEspecialidad(Especialidad especialidad) throws Exception {
        for (Especialidad e : especialidades) {
            if (e.getId() == especialidad.getId()) {
                throw new Exception("Ya existe una especialidad con ID " + especialidad.getId());
            }
        }
        especialidades.add(especialidad);
        guardarListaEspecialidades();
    }

    public Especialidad obtenerEspecialidadPorId(int id) throws Exception {
        for (Especialidad e : especialidades) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new Exception("No se encontró una especialidad con ID " + id);
    }

    public List<Especialidad> listarEspecialidades() {
        return new ArrayList<>(especialidades);
    }

    private List<Especialidad> cargarEspecialidadesDesdeArchivo() throws IOException, Exception {
        List<Especialidad> lista = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_ESPECIALIDADES));
            for (String linea : lineas) {
                lista.add(Especialidad.fromString(linea));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
        return lista;
    }

    private void guardarListaEspecialidades() {
        try {
            List<String> lineas = especialidades.stream()
                    .map(Especialidad::toString)
                    .collect(Collectors.toList());

            Files.write(Paths.get(ARCHIVO_ESPECIALIDADES), lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}

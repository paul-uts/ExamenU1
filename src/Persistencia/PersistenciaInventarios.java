package Persistencia;

import Entidades.EquipoMedico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaInventarios {

    private static final String EQUIPO_MEDICO = "equipoMedico.txt";

    public PersistenciaInventarios() {
        // Asegura que el archivo exista
        File file = new File(EQUIPO_MEDICO);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void agregarEquipoMedico(EquipoMedico equipo) throws Exception {
        if (existeEquipoConId(equipo.getId())) {
            throw new Exception("Ya existe un equipo médico con ID " + equipo.getId());
        }

        if (equipo.getCantidad() < 0) {
            throw new Exception("La cantidad no puede ser negativa.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EQUIPO_MEDICO, true))) {
            String equipoStr = equipo.getId() + "," + equipo.getNombre() + "," + equipo.getCantidad();
            writer.write(equipoStr);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EquipoMedico> listarEquiposMedicos() {
        List<EquipoMedico> equipos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EQUIPO_MEDICO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int cantidad = Integer.parseInt(partes[2]);

                equipos.add(new EquipoMedico(id, nombre, cantidad));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipos;
    }

    // Verifica si ya existe un equipo con ese ID
    public boolean existeEquipoConId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(EQUIPO_MEDICO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int equipoId = Integer.parseInt(partes[0]);
                if (equipoId == id) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualiza la cantidad de un equipo existente
    public void actualizarCantidadEquipo(int id, int cantidad) throws Exception {
        List<EquipoMedico> equipos = listarEquiposMedicos();
        boolean encontrado = false;

        for (EquipoMedico equipo : equipos) {
            if (equipo.getId() == id) {
                if (cantidad < 0) {
                    throw new Exception("La cantidad no puede ser negativa.");
                }
                equipo.setCantidad(cantidad);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("No se encontró equipo médico con ID " + id);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EQUIPO_MEDICO))) {
            for (EquipoMedico equipo : equipos) {
                String equipoStr = equipo.getId() + "," + equipo.getNombre() + "," + equipo.getCantidad();
                writer.write(equipoStr);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String consultarEquipo(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(EQUIPO_MEDICO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int equipoId = Integer.parseInt(partes[0]);
                if (equipoId == id) {
                    String nombre = partes[1];
                    int cantidad = Integer.parseInt(partes[2]);
                    return "ID: " + equipoId + "\nNombre: " + nombre + "\nCantidad: " + cantidad;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;  // Si no se encuentra el equipo, devolvemos null
    }

    public void desinventariarEquipo(int id, int cantidadAEliminar) throws Exception {
        List<EquipoMedico> equipos = listarEquiposMedicos();
        boolean encontrado = false;

        // Buscar el equipo por ID
        for (EquipoMedico equipo : equipos) {
            if (equipo.getId() == id) {
                // Verificamos si la cantidad a eliminar es mayor que la cantidad disponible
                if (cantidadAEliminar > equipo.getCantidad()) {
                    throw new Exception("No hay suficiente cantidad para eliminar.");
                }

                // Reducir la cantidad del equipo
                equipo.setCantidad(equipo.getCantidad() - cantidadAEliminar);
                encontrado = true;

                // Si la cantidad es 0, eliminamos el equipo de la lista
                if (equipo.getCantidad() == 0) {
                    equipos.remove(equipo);
                }
                break;
            }
        }

    }

}

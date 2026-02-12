/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

/**
 *
 * @author Enrique Osuna
 */
import Entidades.*;
import Persistencia.IPersistenciaFachada;
import java.util.Scanner;
import java.util.List;

public class MenuPrincipal {
    
    private IPersistenciaFachada persistencia;
    private Scanner scanner;

    public MenuPrincipal(IPersistenciaFachada persistencia) {
        this.persistencia = persistencia;
        this.scanner = new Scanner(System.in);
    }

    public void mostrar() {
        boolean salir = false;
        while(!salir) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Agregar Paciente");
            System.out.println("2. Agregar Médico");
            System.out.println("3. Agregar Especialidad");
            System.out.println("4. Agregar Equipo Médico");
            System.out.println("5. Programar Consulta");
            System.out.println("6. Listar Datos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();
            switch(opcion) {
                case "1":
                    opcionAgregarPaciente();
                    break;
                case "2":
                    opcionAgregarMedico();
                    break;
                case "3":
                    opcionAgregarEspecialidad();
                    break;
                case "4":
                    opcionAgregarEquipoMedico();
                    break;
                case "5":
                    opcionProgramarConsulta();
                    break;
                case "6":
                    opcionListarDatos();
                    break;
                case "7":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }

    /**
     * Llama al método de IPersistenciaFachada para agregar un nuevo paciente,
     * validando los datos antes de la operación.
     */
    public void opcionAgregarPaciente() {
        try {
            System.out.println("\n== Agregar Paciente ==");
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            
            Paciente paciente = new Paciente(id, nombre, edad, direccion);
            persistencia.agregarPaciente(paciente);
            System.out.println("Paciente agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
        } catch (Exception e) {
            System.out.println("Error al agregar paciente: " + e.getMessage());
        }
    }

    /**
     * Llama al método de IPersistenciaFachada para agregar un nuevo médico,
     * validando los datos antes de la operación.
     */
    public void opcionAgregarMedico() {
        try {
            System.out.println("\n== Agregar Médico ==");
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("ID Especialidad: ");
            int idEspecialidad = Integer.parseInt(scanner.nextLine());
            
            // Se asume que la especialidad ya debe haber sido agregada antes
            Especialidad especialidad = persistencia.obtenerEspecialidadPorId(idEspecialidad);
            
            Medico medico = new Medico(id, nombre, especialidad);
            persistencia.agregarMedico(medico);
            System.out.println("Médico agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
        } catch (Exception e) {
            System.out.println("Error al agregar médico: " + e.getMessage());
        }
    }

    /**
     * Llama al método de IPersistenciaFachada para agregar una nueva especialidad,
     * validando los datos antes de la operación.
     */
    public void opcionAgregarEspecialidad() {
        try {
            System.out.println("\n== Agregar Especialidad ==");
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre de la especialidad: ");
            String nombre = scanner.nextLine();
            
            Especialidad especialidad = new Especialidad(id, nombre);
            persistencia.agregarEspecialidad(especialidad);
            System.out.println("Especialidad agregada correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
        } catch (Exception e) {
            System.out.println("Error al agregar especialidad: " + e.getMessage());
        }
    }

    /**
     * Llama al método de IPersistenciaFachada para agregar un equipo médico,
     * validando los datos antes de la operación.
     */
    public void opcionAgregarEquipoMedico() {
        try {
            System.out.println("\n== Agregar Equipo Médico ==");
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            
            EquipoMedico equipo = new EquipoMedico(id, nombre, cantidad);
            persistencia.agregarEquipoMedico(equipo);
            System.out.println("Equipo médico agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
        } catch (Exception e) {
            System.out.println("Error al agregar equipo médico: " + e.getMessage());
        }
    }

    /**
     * Llama al método de IPersistenciaFachada para programar una consulta,
     * validando que el paciente y el médico existan, y que la fecha sea correcta.
     */
    public void opcionProgramarConsulta() {
        try {
            System.out.println("\n== Programar Consulta ==");
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("ID Paciente: ");
            int idPaciente = Integer.parseInt(scanner.nextLine());
            System.out.print("ID Médico: ");
            int idMedico = Integer.parseInt(scanner.nextLine());
            System.out.print("Fecha (yyyy-MM-dd): ");
            String fecha = scanner.nextLine();

            // Se obtienen las entidades desde la persistencia para validarlas
            Paciente paciente = persistencia.obtenerPacientePorId(idPaciente);
            Medico medico = persistencia.obtenerMedicoPorId(idMedico);

            Consulta consulta = new Consulta(id, paciente, medico, fecha);
            persistencia.programarConsulta(consulta);
            System.out.println("Consulta programada correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número inválido.");
        } catch (Exception e) {
            System.out.println("Error al programar consulta: " + e.getMessage());
        }
    }

    /**
     * Menú para listar pacientes, médicos, especialidades, equipos y consultas.
     */
    public void opcionListarDatos() {
        boolean salir = false;
        while(!salir) {
            System.out.println("\n=== Menú de Listado ===");
            System.out.println("1. Listar Pacientes");
            System.out.println("2. Listar Médicos");
            System.out.println("3. Listar Especialidades");
            System.out.println("4. Listar Equipos Médicos");
            System.out.println("5. Listar Consultas");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();
            switch(opcion) {
                case "1":
                    listarPacientes();
                    break;
                case "2":
                    listarMedicos();
                    break;
                case "3":
                    listarEspecialidades();
                    break;
                case "4":
                    listarEquiposMedicos();
                    break;
                case "5":
                    listarConsultas();
                    break;
                case "6":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void listarPacientes() {
        try {
            List<Paciente> pacientes = persistencia.listarPacientes();
            if (pacientes.isEmpty()) {
                System.out.println("No hay pacientes registrados.");
            } else {
                System.out.println("\n== Lista de Pacientes ==");
                for (Paciente p : pacientes) {
                    System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre() +
                            ", Edad: " + p.getEdad() + ", Dirección: " + p.getDireccion());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar pacientes: " + e.getMessage());
        }
    }

    private void listarMedicos() {
        try {
            List<Medico> medicos = persistencia.listarMedicos();
            if (medicos.isEmpty()) {
                System.out.println("No hay médicos registrados.");
            } else {
                System.out.println("\n== Lista de Médicos ==");
                for (Medico m : medicos) {
                    System.out.println("ID: " + m.getId() + ", Nombre: " + m.getNombre() +
                            ", Especialidad: " + m.getEspecialidad().getNombre());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar médicos: " + e.getMessage());
        }
    }

    private void listarEspecialidades() {
        try {
            List<Especialidad> especialidades = persistencia.listarEspecialidades();
            if (especialidades.isEmpty()) {
                System.out.println("No hay especialidades registradas.");
            } else {
                System.out.println("\n== Lista de Especialidades ==");
                for (Especialidad e : especialidades) {
                    System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar especialidades: " + e.getMessage());
        }
    }

    private void listarEquiposMedicos() {
        try {
            List<EquipoMedico> equipos = persistencia.listarEquiposMedicos();
            if (equipos.isEmpty()) {
                System.out.println("No hay equipos médicos registrados.");
            } else {
                System.out.println("\n== Lista de Equipos Médicos ==");
                for (EquipoMedico eq : equipos) {
                    System.out.println("ID: " + eq.getId() + ", Nombre: " + eq.getNombre() +
                            ", Cantidad: " + eq.getCantidad());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar equipos médicos: " + e.getMessage());
        }
    }

    private void listarConsultas() {
        try {
            List<Consulta> consultas = persistencia.listarConsultas();
            if (consultas.isEmpty()) {
                System.out.println("No hay consultas registradas.");
            } else {
                System.out.println("\n== Lista de Consultas ==");
                for (Consulta c : consultas) {
                    System.out.println("ID: " + c.getId() + 
                            ", Paciente: " + c.getPaciente().getNombre() +
                            ", Médico: " + c.getMedico().getNombre() +
                            ", Fecha: " + c.getFecha());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar consultas: " + e.getMessage());
        }
    }
}



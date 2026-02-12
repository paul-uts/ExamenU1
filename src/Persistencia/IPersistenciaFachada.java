/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.*;
import Persistencia.*;

/**
 *
 * @author Enrique Osuna
 */
import java.util.List;

public interface IPersistenciaFachada {

    // Pacientes
    void agregarPaciente(Paciente paciente) throws Exception;

    Paciente obtenerPacientePorId(int id) throws Exception;

    List<Paciente> listarPacientes() throws Exception;

    // Médicos
    void agregarMedico(Medico medico) throws Exception;

    Medico obtenerMedicoPorId(int id) throws Exception;

    List<Medico> listarMedicos() throws Exception;

    // Especialidades
    void agregarEspecialidad(Especialidad especialidad) throws Exception;

    Especialidad obtenerEspecialidadPorId(int id) throws Exception;

    List<Especialidad> listarEspecialidades() throws Exception;

    // Equipos Médicos
    void agregarEquipoMedico(EquipoMedico equipo) throws Exception;

    void actualizarCantidadEquipo(int id, int cantidad) throws Exception;

    List<EquipoMedico> listarEquiposMedicos() throws Exception;

    // Consultas
    void programarConsulta(Consulta consulta) throws Exception;

    List<Consulta> listarConsultas() throws Exception;

}

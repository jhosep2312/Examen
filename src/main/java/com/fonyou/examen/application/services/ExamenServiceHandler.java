package com.fonyou.examen.application.services;

import com.fonyou.examen.application.repositories.EstudianteRepository;
import com.fonyou.examen.application.repositories.ExamenEstudianteRepository;
import com.fonyou.examen.application.repositories.ExamenRepository;
import com.fonyou.examen.core.entity.*;
import com.fonyou.examen.core.events.RequestEvent;
import com.fonyou.examen.core.events.ResponseCode;
import com.fonyou.examen.core.events.ResponseEvent;
import com.fonyou.examen.core.firmServices.ExamenService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExamenServiceHandler implements ExamenService {

    private final ExamenRepository examenRepository;

    private final EstudianteRepository estudianteRepository;

    private final ExamenEstudianteRepository examenEstudianteRepository;

    public ExamenServiceHandler(ExamenRepository examenRepository, EstudianteRepository estudianteRepository, ExamenEstudianteRepository examenEstudianteRepository) {
        this.examenRepository = examenRepository;
        this.estudianteRepository = estudianteRepository;
        this.examenEstudianteRepository = examenEstudianteRepository;
    }

    @Override
    public ResponseEvent<Examen> createExamen(RequestEvent<Examen> requestEvent) {
        try {

            var request = requestEvent.getRequest();

            if (request.getPreguntas().isEmpty()) {
                return new ResponseEvent<Examen>().badRequest("ingrese por lo menos una pregunta ");
            } else {

                for (Pregunta pregunta : request.getPreguntas()) {

                    if (pregunta.getRespuestas().isEmpty()) {
                        return new ResponseEvent<Examen>().badRequest("Se deben ingresar 4 respuestas por cada pregunta ");
                    } else {

                        if (pregunta.getRespuestas().size() != 4) {
                            return new ResponseEvent<Examen>().badRequest("Se deben ingresar 4 respuestas por cada pregunta ");
                        } else {

                            int respuestasCorrectas = 0;

                            for (Respuesta respuesta : pregunta.getRespuestas()) {
                                respuestasCorrectas += respuesta.isEsCorrecta() ? 1 : 0;
                            }

                            if (respuestasCorrectas != 1) {
                                return new ResponseEvent<Examen>().badRequest("Debe existir una respuesta correcta (solo una por pregunta)");
                            }

                            ResponseEvent<Examen> responseValid = validPorcentage(request);

                            if (responseValid.getCode() != ResponseCode.OK) {

                                return responseValid;
                            }
                        }
                    }
                }
            }

            var examen = examenRepository.save(request);

            return new ResponseEvent<Examen>().ok("El examen se guardo correctamente !", ((Examen) examen));
        } catch (Exception ex) {
            return new ResponseEvent<Examen>().badRequest("Error al intentar crear el examen");
        }
    }

    @Override
    public ResponseEvent<Examen> assignExamenEstudiante(Long idExamen, String zonaHoraria, String fechaExamen) {

        Optional<Examen> examen = examenRepository.findById(idExamen);

        if (!examen.isPresent()) {
            return new ResponseEvent<Examen>().badRequest("Examen no encontrado");
        } else {
            try {
                examen.get().setFechaExamen(LocalDate.parse(fechaExamen));
            } catch (Exception e) {
                return new ResponseEvent<Examen>().badRequest("El formato de la fecha debe ser = yyyy-mm-dd");
            }

            List<Estudiante> estudiantes = estudianteRepository.findByZonaHorariaCiudad(zonaHoraria.trim());

            if (estudiantes.size() == 0) {
                return new ResponseEvent<Examen>().badRequest("No existen estudiantes en la zona Horaria "+ zonaHoraria);
            }

            for (int i = 0; i < estudiantes.size(); i++) {

                ExamenEstudiante examenEstudiante = new ExamenEstudiante();

                examenEstudiante.setEstudianteId(estudiantes.get(i).getEstudianteId());

                examenEstudiante.setExamenId(idExamen);

                examenEstudianteRepository.save(examenEstudiante);

            }

        }

        return new ResponseEvent<Examen>().ok("El examen se ha asignado correctamente a los estudiantes" +
                "de la zona horaria " + zonaHoraria);
    }

    ResponseEvent<Examen> validPorcentage(Examen examen) {

        float porcentageFinal = 0.0F;

        for (Pregunta pregunta : examen.getPreguntas()) {

            if (pregunta.getPorcentaje() < 0 && pregunta.getPorcentaje() > 100) {

                return new ResponseEvent<Examen>().badRequest("Los porcentajes deben ser validos");
            } else {
                porcentageFinal += pregunta.getPorcentaje();
            }
        }

        if (porcentageFinal != 100) {
            return new ResponseEvent<Examen>().badRequest("El porcentaje de las preguntas debe sumar total a 100");
        }

        return new ResponseEvent<Examen>().ok("");
    }

}


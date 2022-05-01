package com.fonyou.examen.application.services;

import com.fonyou.examen.application.repositories.EstudianteRepository;
import com.fonyou.examen.application.repositories.ExamenEstudianteRepository;
import com.fonyou.examen.core.entity.Estudiante;
import com.fonyou.examen.core.events.RequestEvent;
import com.fonyou.examen.core.events.ResponseEvent;
import com.fonyou.examen.core.firmServices.EstudianteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteServiceHandler implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    private final ExamenEstudianteRepository examenEstudianteRepository;

    public EstudianteServiceHandler(EstudianteRepository estudianteRepository, ExamenEstudianteRepository examenEstudianteRepository) {
        this.estudianteRepository = estudianteRepository;
        this.examenEstudianteRepository = examenEstudianteRepository;
    }

    @Override
    public ResponseEvent<Estudiante> createEstudiante(RequestEvent<Estudiante> requestEvent) {

        try {
            estudianteRepository.save((Estudiante) requestEvent.getRequest());

            return new ResponseEvent<Estudiante>().ok("Se guardo correctamente el estudiante ", requestEvent.getRequest());
        } catch (Exception e) {
            return new ResponseEvent<Estudiante>().badRequest("Hubo un error al intentar cargar el estudiante ");
        }

    }

    @Override
    public ResponseEvent<Estudiante> collectAnswersEstudiante(Long idEstudiante) {

        /*Optional<Estudiante> estudiante = examenEstudianteRepository.findById(idEstudiante);

        if(!estudiante.isPresent()){

            return new ResponseEvent<Estudiante>().badRequest("Estudiante no encontrado ");
        }else{

            estudiante.get().ge

        }
*/
        return null;
    }

}

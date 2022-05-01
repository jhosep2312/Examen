package com.fonyou.examen.core.firmServices;

import com.fonyou.examen.core.entity.Estudiante;
import com.fonyou.examen.core.events.RequestEvent;
import com.fonyou.examen.core.events.ResponseEvent;
import org.springframework.stereotype.Service;

public interface EstudianteService {

    ResponseEvent<Estudiante> createEstudiante(RequestEvent<Estudiante> requestEvent);

    ResponseEvent<Estudiante> collectAnswersEstudiante(Long requestEvent);
}

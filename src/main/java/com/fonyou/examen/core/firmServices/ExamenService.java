package com.fonyou.examen.core.firmServices;

import com.fonyou.examen.core.entity.Examen;
import com.fonyou.examen.core.events.RequestEvent;
import com.fonyou.examen.core.events.ResponseEvent;

public interface ExamenService {

    ResponseEvent<Examen> createExamen(RequestEvent<Examen> examen);

    ResponseEvent<Examen> assignExamenEstudiante(Long idExamen, String zonaHoraria, String fechaExamen);
}

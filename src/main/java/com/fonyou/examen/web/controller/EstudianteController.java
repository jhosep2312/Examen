package com.fonyou.examen.web.controller;

import com.fonyou.examen.core.entity.Estudiante;
import com.fonyou.examen.core.entity.Examen;
import com.fonyou.examen.core.events.RequestEvent;
import com.fonyou.examen.core.events.ResponseEvent;
import com.fonyou.examen.core.firmServices.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fonyou.examen.web.controller.APIController.buildHttpResponse;

@RestController
@RequestMapping("estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<ResponseEvent<Estudiante>> createEstudiante(
            @RequestBody Estudiante request) {
        ResponseEvent<Estudiante> responseEvent = estudianteService.createEstudiante(new RequestEvent<>(request));
        return buildHttpResponse(responseEvent);
    }

    @GetMapping("collect-answers-estudiante/{idEstudiante}")
    public ResponseEntity<ResponseEvent<Estudiante>> collectAnswersEstudiante(
            @PathVariable("idEstudiante") Long idEstudiante ) {

        ResponseEvent<Estudiante> responseEvent = estudianteService.collectAnswersEstudiante(idEstudiante);

        return buildHttpResponse(responseEvent);
    }

}

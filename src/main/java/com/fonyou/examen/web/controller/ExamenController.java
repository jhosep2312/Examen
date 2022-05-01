package com.fonyou.examen.web.controller;

import com.fonyou.examen.core.entity.Examen;
import com.fonyou.examen.core.events.RequestEvent;
import com.fonyou.examen.core.events.ResponseEvent;
import com.fonyou.examen.core.firmServices.ExamenService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.fonyou.examen.web.controller.APIController.buildHttpResponse;

@RestController
@RequestMapping("examen")
public class ExamenController {

    private final ExamenService examenService;

    ExamenController(ExamenService examenService) {

        this.examenService = examenService;
    }

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<ResponseEvent<Examen>> createExamen(
            @RequestBody @Validated Examen request) {
        ResponseEvent<Examen> responseEvent = examenService.createExamen(new RequestEvent<>(request));
        return buildHttpResponse(responseEvent);
    }

    @GetMapping("assign-examen/{idExamen}/{zonaHoraria}/{fechaExamen}")
    public ResponseEntity<ResponseEvent<Examen>> assignExamen(
            @PathVariable("idExamen") Long idExamen,
            @PathVariable("zonaHoraria") String zonaHoraria,
            @PathVariable("fechaExamen") String fechaExamen) {

        ResponseEvent<Examen> responseEvent = examenService.assignExamenEstudiante(idExamen, zonaHoraria, fechaExamen);

        return buildHttpResponse(responseEvent);
    }

}

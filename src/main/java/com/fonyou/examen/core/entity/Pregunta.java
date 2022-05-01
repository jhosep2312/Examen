package com.fonyou.examen.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "pregunta")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preguntaId;

    @NotNull
    private String pregunta;

    private float porcentaje;

    private LocalDate fechaExamen;

    @NotNull
    @OneToMany(mappedBy = "respuestaId")
    private List<Respuesta> respuestas;
}

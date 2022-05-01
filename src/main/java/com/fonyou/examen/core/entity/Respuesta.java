package com.fonyou.examen.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pregunta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long respuestaId;

    @NotNull
    @NotEmpty
    private String respuesta;

    @NotNull
    private boolean esCorrecta;
}

package com.fonyou.examen.core.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "examenEstudiante")
public class ExamenEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examenEstudianteId;

    Long examenId;

    Long estudianteId;

    @OneToMany(mappedBy = "examenId")
    private List<Examen> examenes;

    @OneToMany(mappedBy = "estudianteId")
    private List<Estudiante> estudiantes;

}

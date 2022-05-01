package com.fonyou.examen.core.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "examen")
@NoArgsConstructor
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examenId;

    private String nombreExamen;

    private LocalDate fechaExamen;

    @OneToMany(mappedBy = "preguntaId")
    private List<Pregunta> preguntas;



}

package com.fonyou.examen.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estudianteId;

    private  String nombre;

    private int edad;

    private String ciudad;

    private String zonaHorariaCiudad;
}

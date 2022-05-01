package com.fonyou.examen.application.repositories;

import com.fonyou.examen.core.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    List<Estudiante> findByZonaHorariaCiudad(String zonaHoraria);
}

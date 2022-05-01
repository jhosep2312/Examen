package com.fonyou.examen.application.repositories;

import com.fonyou.examen.core.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamenRepository extends JpaRepository<Examen,Long> {
}

package com.agendaFamiliar.repository;

import com.agendaFamiliar.entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, String>{
    
}

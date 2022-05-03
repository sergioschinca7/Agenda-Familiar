package com.agendaFamiliar.repository;

import com.agendaFamiliar.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, String>{
    
}

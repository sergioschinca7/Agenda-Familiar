package com.agendaFamiliar.repository;

import com.agendaFamiliar.entity.Hijo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HijoRepository extends JpaRepository<Hijo, String>{
    
}

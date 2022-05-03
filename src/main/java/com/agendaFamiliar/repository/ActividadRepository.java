
package com.agendaFamiliar.repository;

import com.agendaFamiliar.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, String> {
    
}

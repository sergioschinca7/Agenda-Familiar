package com.agendaFamiliar.repository;

import com.agendaFamiliar.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, String> {
    
    @Query ("SELECT r FROM Registro r WHERE r.username = :username")
    public Registro findByUsername(@Param("username")String username);
}

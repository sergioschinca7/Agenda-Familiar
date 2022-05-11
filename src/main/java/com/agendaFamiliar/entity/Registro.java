package com.agendaFamiliar.entity;


import com.agendaFamiliar.enums.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;


@Entity
@Data
public class Registro extends Usuario {
    
    private String username;
    
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;
   
    
}

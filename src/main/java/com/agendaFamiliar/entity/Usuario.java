package com.agendaFamiliar.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
    private String apellido;
    
    private String email;
    
    private LocalDate fechNacimiento;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hijo> hijos = new ArrayList<Hijo>();
    
    private String password;
      
}

package com.agendaFamiliar.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Clinica implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
    private String direccion;
    
    private String medico;
    
    private String estudio;
    
    private LocalDate fecha;
    
    private String archivo;
    
    private String observaciones;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_historiaClinica")
    private HistoriaClinica historiaClinica; 
    
}

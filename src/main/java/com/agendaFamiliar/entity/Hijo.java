package com.agendaFamiliar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Hijo implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
    private String apellido;
    
    private String dni;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date fechNacimiento;
    
    private String grupoSanguineo;
    
    private ArrayList<String> fotos;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "hijo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Actividad> actividades = new ArrayList<Actividad>();
    
    @OneToOne(cascade = CascadeType.ALL)
    private HistoriaClinica historiaClinica;
    
    
}

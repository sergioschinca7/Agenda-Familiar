package com.agendaFamiliar.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
public class Actividad implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private LocalDate fecha;
    
    private String titulo;
    
    private String descripcion;
    
//    private List<String> foto;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_hijo")
    private Hijo hijo;
            
    
}

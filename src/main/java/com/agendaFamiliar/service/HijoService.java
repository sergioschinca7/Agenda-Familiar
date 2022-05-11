package com.agendaFamiliar.service;

import com.agendaFamiliar.entity.Hijo;
import com.agendaFamiliar.repository.HijoRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HijoService {

    @Autowired
    private HijoRepository hijoRepository;

    @Transactional
    public Hijo guardar(Hijo hijo) {
        return hijoRepository.save(hijo);
    }

    @Transactional
    public List<Hijo> listarHijos(String id) {
        return hijoRepository.findAllById(id);
    }

    @Transactional
    public void eliminar(String id) {
        hijoRepository.deleteById(id);
    }

  
    
    

}

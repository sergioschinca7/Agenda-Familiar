package com.agendaFamiliar.controller;

import com.agendaFamiliar.entity.Hijo;
import com.agendaFamiliar.entity.Usuario;
import com.agendaFamiliar.service.HijoService;
import com.agendaFamiliar.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hijos")
public class HijoController {
        
    @Autowired
    private HijoService hijoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/cargar")
    public String cargar(Model model){
        System.out.println("\n entro a hijo/cargar \n");
        
        String id1 = "5495ea00-ab87-49de-9392-dd0c50b53967";
        Usuario usuario = usuarioService.buscar(id1);
        System.out.println("usuario : " + usuario);
        
        Hijo hijo = new Hijo();
        hijo.setUsuario(usuario);
        
        System.out.println("\nhijo.usuario : " + hijo.getUsuario());
        
        model.addAttribute("hijo", hijo);
        return "registroHijos";
    }
    
    @PostMapping("/guardar")
    public String guardar(Model model, Hijo hijo){
        System.out.println("\n entro a hijo/guardar \n");
        
        try{
            System.out.println("hijo : " + hijo);
            hijoService.guardar(hijo);
            System.out.println("hijo : " + hijo);
        }catch(Exception ex){
            System.out.println("ex : " + ex.getMessage());
        }
        return "panel_control";
    }
    
    @GetMapping("/listar")
    public List<Hijo> listarHijos(Model model, @PathVariable("id") String id){
        System.out.println("\n entro a listarHijos \n");
        return hijoService.listarHijos(id);
    }
    
    
    
    
}

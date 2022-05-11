package com.agendaFamiliar.controller;

import com.agendaFamiliar.entity.Usuario;
import com.agendaFamiliar.service.RegistroService;
import com.agendaFamiliar.service.WebException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registro")
public class RegistroController {
    
    @Autowired
    private RegistroService usuarioPassService;
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @PostMapping("")
    public String registroSave(Model model,Usuario usuario, 
                                @RequestParam String username, 
                                @RequestParam String password, 
                                @RequestParam String password2, String dni){
        
        try {
            usuarioPassService.save(usuario, username, password, password2, dni);
            return "redirect:/";
            
        } catch (WebException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("username", username);
            return "registro";
        }   
    }
    
}

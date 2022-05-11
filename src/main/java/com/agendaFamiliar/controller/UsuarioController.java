package com.agendaFamiliar.controller;

import com.agendaFamiliar.entity.Usuario;
import com.agendaFamiliar.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cargar")
    public String cargar(Model model) {
        System.out.println("\nentro a usuario/cargar\n");
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "registroPadres";
    }

    @PostMapping("/guardar")
    public String guardar(Model model, Usuario usuario) {
        System.out.println("\nentro a usuario/guardar\n");
        try {
            Usuario u1 = new Usuario();
            u1 = usuarioService.guardar(usuario);
            System.out.println("u1 : " + u1);
        } catch (Exception ex) {
            System.out.println("error al guardar" + ex);
        }
        return "panel_control";
    }

    @GetMapping("/listar")
    public List<Usuario> listar() {
        System.out.println("\nentro a usuario/listar\n");
        return usuarioService.listarUsuarios();
    }

//    @GetMapping()
//    public List<Usuario> listar() {
//        return usuarioService.listaUsuario();
//    }
//    
//    @PostMapping()
//    public Usuario guardar(@RequestBody Usuario usuario) {
//        return usuarioService.guardar(usuario);
//    }
}

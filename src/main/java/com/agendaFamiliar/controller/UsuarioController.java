package com.agendaFamiliar.controller;

import com.agendaFamiliar.entity.Usuario;
import com.agendaFamiliar.service.UsuarioService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public String guardar(Model model, Usuario usuario 
                        ,@RequestParam(name = "file", required = false) MultipartFile foto
                        ) throws ParseException {
        System.out.println("\nentro a usuario/guardar\n");

        try {

            Usuario u1 = new Usuario();
            System.out.println("u1 sin foto :" + u1);

            System.out.println("usuario : " + usuario);
            
            if (!foto.isEmpty()) { //si la foto no viene vacia q la guarde
                String ruta = "C://Agenda//uploads//";
                // nombre con uuid para que no se repita nunca el nmbre de la foto convertida a String
                String nombreFoto = UUID.randomUUID().toString() + "-" + foto.getOriginalFilename();
                Path path = Paths.get(ruta); // path donde 

                // consulta si existe la Directorio sino la crea
                if (!Files.exists(path)) {
                    File directorio = new File("C://Agenda//uploads//");
                    if (directorio.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                } else {
                    System.out.println("\nel directorio C://Agenda//uploads// existe \n\n");
                }

                try {
                    //alamceno los bytes de la foto
                    byte[] bytes = foto.getBytes();
                    //rutaabs concatenada con el nombre de la foto
                    Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);
                    //  aqui guarda fisicamente en el directorio la foto
                    Files.write(rutaAbsoluta, bytes);

                    usuario.setFoto(nombreFoto);

                } catch (Exception ex) {
                    System.err.println(ex);
                }

                u1 = usuarioService.cargarFoto(usuario, foto);
                System.out.println("u1 con foto :" + u1);

                u1 = usuarioService.guardar(usuario);

                System.out.println("u1 : " + u1);
            }
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

    @GetMapping("/buscar")
    public String buscar(Model model) {
        System.out.println("\nentro a usuario/buscar\n");
        
        String id1 = "d760ab71-bd94-48dc-b9de-c5f68cd5cdaa";
        Usuario usuario = usuarioService.buscar(id1);
        System.out.println("usuario : " + usuario);
        
        model.addAttribute("usuario", usuario);
        return "mostrarUsuario";
            
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

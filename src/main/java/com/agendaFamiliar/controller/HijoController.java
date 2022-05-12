package com.agendaFamiliar.controller;

import com.agendaFamiliar.entity.Hijo;
import com.agendaFamiliar.entity.Usuario;
import com.agendaFamiliar.service.HijoService;
import com.agendaFamiliar.service.UsuarioService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
@RequestMapping("/hijos")
public class HijoController {

    @Autowired
    private HijoService hijoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cargar")
    public String cargar(Model model) {
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
    public String guardar(Model model, Hijo hijo, @RequestParam(name = "files", required = false) MultipartFile fotos[]) {
        System.out.println("\n entro a hijo/guardar \n");

        ArrayList<String> lista = new ArrayList();

        try {
            //tratamiento de las fotos
            for (int i = 0; i < fotos.length; i++) {

                MultipartFile file = fotos[i];
//                if (!ar.isEmpty()) { //si la foto no viene vacia q la guarde
                String ruta = "C://Agenda//uploads//"; // ruta relativa donde voy a guardar la foto
                // nombre con uuid para que no se repita nunca el nmbre de la foto convertida a String
                String nombreFoto = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
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
                    byte[] bytes = file.getBytes(); //alamceno los bytes de la foto
                    Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);//ruta abs concatenada con el nombre de la foto

                    Files.write(rutaAbsoluta, bytes);

                    System.out.println("nombreFoto : " + nombreFoto);
                    System.out.println("\n Hijo antes de la lista de fotos : " + hijo);
                    lista.add(nombreFoto);

                } catch (Exception ex) {
                    System.err.println(ex);
                }
                
            }
            hijo.setFotos(lista);
            System.out.println("\n hijo antes de guardar : " + hijo);
            hijoService.guardar(hijo);
            System.out.println("\n hijo guardado : " + hijo);
        } catch (Exception ex) {
            System.out.println("ex : " + ex.getMessage());
        }
        return "panel_control";
    }

    @GetMapping("/listar")
    public String listarHijos(Model model) {
        System.out.println("\n entro a hijos/listar \n");
        
        String id1 = "5495ea00-ab87-49de-9392-dd0c50b53967";
        
        List<Hijo> hijos = new ArrayList<Hijo>();
        hijos =  hijoService.listarHijos(id1);
        
        for (Hijo hijo : hijos) {
            System.out.println("hijo : " + hijo);
        }
        
        model.addAttribute("hijos", hijos);
//        return hijos;
        
        return "listaHijos";
    }
    

}

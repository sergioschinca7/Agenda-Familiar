package com.agendaFamiliar.service;

import com.agendaFamiliar.entity.Usuario;
import com.agendaFamiliar.repository.UsuarioRepository;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario buscar(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminar(String id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario cargarFoto(Usuario usuario, @RequestParam(name = "file", required = false) MultipartFile foto) {
        
//        usuario = buscar(id);
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

//            guardar(usuario);
          
            System.out.println("\n usuario guardado con foto : " + usuario);

        }
        return usuario;
    }
    
    
}

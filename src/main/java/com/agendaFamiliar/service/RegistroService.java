package com.agendaFamiliar.service;

import com.agendaFamiliar.entity.Registro;
import com.agendaFamiliar.entity.Usuario;
import com.agendaFamiliar.enums.Role;

import com.agendaFamiliar.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.agendaFamiliar.repository.RegistroRepository;

@Service
public class RegistroService implements UserDetailsService{
    
    @Autowired
    private RegistroRepository registroRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public Registro save(Usuario usuario, String username, String password, String password2, String dni) throws WebException    {
        
        Registro registro = new Registro();
        
        if(!usuario.getDni().isEmpty() || usuario.getDni() != null){
            throw new WebException("Ya existe un Usuario con ese Dni");
        }        
        if(username.isEmpty() || username == null){
            throw new WebException("El usuario no puede estar vacio");
        }
        if(password.isEmpty() || password == null){
            throw new WebException("El password no puede estar vacio");
        }
        if(password2.isEmpty() || password2 == null){
            throw new WebException("El password2 no puede estar vacio");
        }
        if(!password.equals(password2)){
            throw new WebException("No coinciden los password ingresados");
        }
        
        registro.setUsername(username);
        // con la dependencia de Security y el metodo BCryp... encriptamos un password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        registro.setPassword(encoder.encode(password));
        registro.setRole(Role.USER);
        
        return registroRepository.save(registro);
           
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        try {
            Registro usuario = registroRepository.findByUsername(username);
            User user;
            
            // esto crea una lista de permisos = authorities
            List<GrantedAuthority> authorities = new ArrayList<>();
            
            // agregamos permisos
            authorities.add(new SimpleGrantedAuthority("ROLE"+ usuario.getRole()));
//            authorities.add(new SimpleGrantedAuthority("CLIENTE1"));
//            authorities.add(new SimpleGrantedAuthority("CLIENTE2"));

            return new User(username, usuario.getPassword(), authorities);
            
        } catch (Exception e) {
            throw new UsernameNotFoundException("no se encontro ese usuario");
        }
       
    }
    
}

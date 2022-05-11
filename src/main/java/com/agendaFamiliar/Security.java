package com.agendaFamiliar;

import com.agendaFamiliar.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration      // esta le indica a Spring como ir configurando la aplicacion
@EnableWebSecurity  // esta habilita la seguridad web
// esto nos permite agregar PROPIEDADES a nuestra aplicacion
// y el prePost nos permite autorizar las url, hacer pre_authorize o post_authorize
// eso significa que nos va a permitir que tal ROLE acceda a una URL
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class Security extends WebSecurityConfigurerAdapter{
        
    @Autowired
    private RegistroService usuarioPassService;
       
    // crear un metodo que va a configurar la autenticacion
    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioPassService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    // configurar las peticiones http, Ej, login - logout
//    // este metodo tambien sirve para crear la dependencias de Security y NO TENER
//    // que estar poneindo cada vez que accedo al localhost usuario y contraseña
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/*").permitAll();
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/*", "/img/*", "/html/*", "/js/*" ).permitAll()
                .and()  // todo este parrafo es para el login
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/") // esto es para cuando el ususario se logee a que pag va a ir
                .loginProcessingUrl("loginCheck") // este "loginCheck" debe ser igual a cuando lo llame desde el html
                .failureUrl("/login?error=error") // a este url va si sucede que no se logeo
                .permitAll()
                
                .and()  // todo este parrafo es para el logout
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // si se hizo un logout exitoso viene a este url
                
                .and()
                .csrf().disable()   // esto desabilita a otro usuario acceder a nuestro login
                ;
    }
    
    
}

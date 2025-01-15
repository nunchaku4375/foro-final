package Foro.mecanicos.api.infra.secutity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigration {

    private SecurituFilter securituFilter;


    public SecurityConfigration(SecurituFilter securituFilter) {
        this.securituFilter = securituFilter; // Inyección del filtro personalizado
    }
    //aqui esta el login//
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configurar sesión stateless
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()// Permitir acceso al endpoint /login
                                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                )
                .addFilterBefore(securituFilter, UsernamePasswordAuthenticationFilter.class) // Agregar filtro personalizado
                .build();
    }



        @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //esto decifra la clave secreta del password//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}



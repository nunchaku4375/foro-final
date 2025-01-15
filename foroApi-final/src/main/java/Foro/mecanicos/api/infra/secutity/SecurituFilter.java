package Foro.mecanicos.api.infra.secutity;

import Foro.mecanicos.api.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurituFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServices tokenServices;
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("llamando al filtro");
        var authHeder = request.getHeader("Authorization");
        if (authHeder != null){
            var token = authHeder.replace("Bearer ","").trim();
            var subject = tokenServices.getSubject(token);
            if(subject != null){
                var usuario = usuarioRepo.findByPerfil(subject);
                var autntication = new UsernamePasswordAuthenticationToken(usuario , null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autntication);
            }
        }

        filterChain.doFilter(request,response);

    }
}

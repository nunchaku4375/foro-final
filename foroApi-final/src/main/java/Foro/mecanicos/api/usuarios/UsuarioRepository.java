package Foro.mecanicos.api.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //lo encuentra por nombre de usuario en el repository "B.D."//
    UserDetails findByPerfil(String username);
}

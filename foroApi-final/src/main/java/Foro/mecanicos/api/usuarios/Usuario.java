package Foro.mecanicos.api.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

//este odigo se encarga de mandar las informaciones al la B.D.//
@Getter
@Table(name = "usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String perfil;
    private String clave;

    public Long getId() {
        return id;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getClave() {
        return clave;
    }

    //constructor que por defaul me da el UserSerails para el ingrso con usuario y contrace;a//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return perfil;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

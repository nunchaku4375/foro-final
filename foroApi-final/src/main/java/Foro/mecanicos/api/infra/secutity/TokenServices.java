package Foro.mecanicos.api.infra.secutity;

import Foro.mecanicos.api.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServices {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                    return JWT.create()
                    .withIssuer("foro-mecanicos")
                    .withSubject(usuario.getPerfil())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new IllegalArgumentException("El token no puede ser nulo.");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Validando la firma
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("foro-mecanicos")
                    .build()
                    .verify(token); // Verifica el token y devuelve el DecodedJWT
            String subject = jwt.getSubject(); // Obtén el 'subject'
            if (subject == null) {
                throw new RuntimeException("El token no contiene un subject válido.");
            }
            return subject;
        } catch (JWTVerificationException exception) {
            // Registra el error y lanza una excepción personalizada
            throw new RuntimeException("Token inválido o no verificable: " + exception.getMessage(), exception);
        }
    }




    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-05:00"));
    }

}

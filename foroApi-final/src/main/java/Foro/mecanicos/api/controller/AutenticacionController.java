package Foro.mecanicos.api.controller;

import Foro.mecanicos.api.infra.secutity.DatosJWToken;
import Foro.mecanicos.api.infra.secutity.TokenServices;
import Foro.mecanicos.api.usuarios.DatosAutenticacionUsuario;
import Foro.mecanicos.api.usuarios.Usuario;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//este codigo se encarga de hacer un inicio de session (solo usuarios en la B.D.)//
@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenServices tokenServices;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authtoken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.perfil(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        var jwtToken = tokenServices.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWToken(jwtToken));
    }



}

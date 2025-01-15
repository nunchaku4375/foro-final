package Foro.mecanicos.api.controller;

import Foro.mecanicos.api.preguntas.*;
import Foro.mecanicos.api.respuestas.DatosRespuestaPregunta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;

@RestController
@RequestMapping("/registro")
@SecurityRequirement(name = "bearer-key")
public class RegistroController {

    //atrae los datos del Repositorio//
    @Autowired
    private RegistroPreguntasRepo registroPreguntasRepo;

    //recibe la solicitud del cliente//
    @PostMapping
    public ResponseEntity<DatosRegistroPregunta> registrarPregunta(@RequestBody @Valid DatosRegistroPreguntas datosRegistroPreguntas,
                                  UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("llego bien ");
        RegistroPreguntas preguntas = registroPreguntasRepo.save(new RegistroPreguntas(datosRegistroPreguntas));
        DatosRegistroPregunta datosRegistroPregunta = new DatosRegistroPregunta(preguntas.getId(), preguntas.getIdUsuario(),
                preguntas.getNombreUsuario(), preguntas.getTitulo(),
                preguntas.getMensaje(), preguntas.getRespuesta());
        URI url = uriComponentsBuilder.path("/registro/{id}").buildAndExpand(preguntas.getId()).toUri();
        return ResponseEntity.created(url).body(datosRegistroPregunta);
    }

    //obtiene una LISTA de las preguntas sin contestar//
    @GetMapping("/preguntasSinContestar")
    public ResponseEntity<Page<DatosListaPreguntas>> preguntasSinContestadas(@PageableDefault(size = 5) Pageable paginacion){
         return ResponseEntity.ok(registroPreguntasRepo.findByActivoTrue(paginacion).map(DatosListaPreguntas::new));

    }

    //obtiene una LISTA de las preguntas ya contestadas//
    @GetMapping("/preguntasContestadas")
    @Transactional
    public ResponseEntity<Page<DatosListaPreguntas>> preguntasContestadas(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(registroPreguntasRepo.findByActivoFalse(paginacion).map(DatosListaPreguntas::new));
    }

    //Responde la pregunta y actualiza//
    @PutMapping
    @Transactional
    public ResponseEntity respondePregunta(@RequestBody @Valid DatosRespuestaPregunta respuestaPregunta){
        RegistroPreguntas preguntas = registroPreguntasRepo.getReferenceById(respuestaPregunta.id());
        preguntas.actualizarRespuesta(respuestaPregunta);
        return ResponseEntity.ok(new DatosRegistroPregunta(preguntas.getId(), preguntas.getIdUsuario(),
                preguntas.getNombreUsuario(), preguntas.getTitulo(),
                preguntas.getMensaje(), preguntas.getRespuesta()));
    }


    //desactiva la pregunta que ya fue constestada
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivaPreguntaContestada(@PathVariable Long id){
        RegistroPreguntas preguntas = registroPreguntasRepo.getReferenceById(id);
        preguntas.desactivarPregunta();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")

    public ResponseEntity <DatosRegistroPregunta> retornaListaPreguntas(@PathVariable Long id){
        RegistroPreguntas preguntas = registroPreguntasRepo.getReferenceById(id);
        var datosListaPreguntas = new DatosRegistroPregunta(preguntas.getId(), preguntas.getIdUsuario(),
                preguntas.getNombreUsuario(), preguntas.getTitulo(),
                preguntas.getMensaje(), preguntas.getRespuesta());
        return ResponseEntity.ok(datosListaPreguntas);
    }


}

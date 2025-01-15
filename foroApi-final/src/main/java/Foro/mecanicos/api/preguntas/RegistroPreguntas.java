package Foro.mecanicos.api.preguntas;


import Foro.mecanicos.api.respuestas.DatosRespuestaPregunta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


//crea la tabla en la BD con los siguientes datos y crea la entidad//
@Table(name = "registropreguntas")
@Entity(name = "PREGUNTAS")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class RegistroPreguntas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idUsuario;
    private String nombreUsuario;
    private String titulo;
    private String mensaje;
    private String respuesta;
    private Boolean activo;
    //contructor vacio//
    public RegistroPreguntas() {

    }

    //constructor con atributos //
    public RegistroPreguntas(DatosRegistroPreguntas datosRegistroPreguntas) {
        this.activo = true;
        this.idUsuario = datosRegistroPreguntas.idUsuario();
        this.nombreUsuario = datosRegistroPreguntas.nombreUsuario();
        this.titulo = datosRegistroPreguntas.titulo();
        this.mensaje = datosRegistroPreguntas.mensaje();
        this.respuesta = datosRegistroPreguntas.respuesta();
    }

    //getters"lombok no me funciono""//
    public Long getId() {
        return id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Boolean getActivo() {
        return activo;
    }

    //getters ya que @getter no me funciono//
    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getRespuesta() {
        return respuesta;
    }


    public void actualizarRespuesta(DatosRespuestaPregunta respuestaPregunta) {
        if (respuestaPregunta.titulo() != null){
            this.titulo = respuestaPregunta.titulo();
        }
        if (respuestaPregunta.mensaje() != null){
            this.mensaje = respuestaPregunta.mensaje();
        }
        if (respuestaPregunta.respuesta() != null){
            this.respuesta = respuestaPregunta.respuesta();
        }




    }

    public void desactivarPregunta() {
        this.activo = false;

    }
}

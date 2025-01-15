package Foro.mecanicos.api.preguntas;

public record DatosListaPreguntas(Long id, String nombreUsusario, String titulo, String mensaje, String respuesta) {

    //obtiene los datos del json mediante el DTO del record//
    public DatosListaPreguntas(RegistroPreguntas registroPreguntas){
        this(registroPreguntas.getId(), registroPreguntas.getNombreUsuario(),
                registroPreguntas.getTitulo(), registroPreguntas.getMensaje(), registroPreguntas.getRespuesta());
    }
}

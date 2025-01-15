package Foro.mecanicos.api.preguntas;

//de aqui se envian los datos ya registrados al previw//
public record DatosRegistroPregunta(Long id,
                                    String idUsuario,
                                    String nombreUsuario,
                                    String titulo,
                                    String mensaje,
                                    String respuesta
) {
}

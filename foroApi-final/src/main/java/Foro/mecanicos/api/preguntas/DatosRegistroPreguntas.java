package Foro.mecanicos.api.preguntas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//de aqui se atraen los datos del cliente//
public record DatosRegistroPreguntas (
        @NotBlank
        @Pattern(regexp = "\\d{1,4}")
        String idUsuario,
        @NotBlank
        String nombreUsuario,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        String respuesta){
}

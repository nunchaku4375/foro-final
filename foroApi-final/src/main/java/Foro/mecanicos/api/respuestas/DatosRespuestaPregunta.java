package Foro.mecanicos.api.respuestas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// envia los datos ya con la respuesta incluida//
public record DatosRespuestaPregunta(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        @NotBlank
        String respuesta) {
}

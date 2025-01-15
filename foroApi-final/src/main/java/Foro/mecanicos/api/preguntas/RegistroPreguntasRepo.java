package Foro.mecanicos.api.preguntas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistroPreguntasRepo extends JpaRepository<RegistroPreguntas, Long> {

    Page<RegistroPreguntas> findByActivoTrue(Pageable paginacion);

    Page<RegistroPreguntas> findByActivoFalse(Pageable paginacion);
}

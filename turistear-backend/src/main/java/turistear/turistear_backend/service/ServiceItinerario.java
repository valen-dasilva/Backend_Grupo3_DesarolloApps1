package turistear.turistear_backend.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import turistear.turistear_backend.dto.ItinerarioDTO;
import turistear.turistear_backend.dto.ItinerarioRequest;
import turistear.turistear_backend.model.Actividad;
import turistear.turistear_backend.model.ItemItinerario;
import turistear.turistear_backend.model.Itinerario;
import turistear.turistear_backend.model.Usuario;
import turistear.turistear_backend.repository.ActividadRepository;
import turistear.turistear_backend.repository.ItinerarioRepository;
import turistear.turistear_backend.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceItinerario {

    private final UsuarioRepository repositorioUsuario;
    private final ItinerarioRepository repositorioItinerario;
    private final ActividadRepository repositorioActividad;

    public ServiceItinerario(UsuarioRepository repositorioUsuario,
                             ItinerarioRepository itinerarioRepository,
                             ActividadRepository repositorioActividad) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioItinerario = itinerarioRepository;
        this.repositorioActividad = repositorioActividad;
    }


    // Correcto:
    @Transactional
    public void crearItinerario(ItinerarioRequest request) {
        // 1. Buscar el usuario REAL en la base
        Usuario creador = repositorioUsuario.findById(request.idCreador())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + request.idCreador()));

        // 2. Construir la entity
        Itinerario itinerario = new Itinerario();
        itinerario.setTitulo(request.titulo());
        itinerario.setDestino(request.destino());
        itinerario.setDescripcion(request.descripcion());
        itinerario.setEsPublico(request.esPublico());
        itinerario.setFechaInicio(request.fechaInicio());
        itinerario.setFechaFin(request.fechaFin());
        itinerario.setFechaCreacion(LocalDateTime.now()); // generado en el backend
        itinerario.setCreador(creador); // ← el usuario COMPLETO, no solo el id

        // 3. Guardar
        creador.getMis_itinerarios().add(itinerario);
        repositorioItinerario.save(itinerario);
    }

//    public Usuario agregarItinerarioAUsuario(Integer idUsuario, Integer idItinerario) {
//
//        Usuario usuario = repositorioUsuario.findById(idUsuario)
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//        Itinerario itinerario = repositorioItinerario.findById(idItinerario)
//                .orElseThrow(() -> new RuntimeException("Itinerario no encontrado"));
//
//        usuario.agregarItinerario(itinerario);
//
//        return repositorioUsuario.save(usuario);
//    }

    public Set<ItinerarioDTO> obtenerItinerariosPorUsuario(Long idUsuario) {

        Usuario usuario = repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuario.getMis_itinerarios()
                .stream()
                .map(ItinerarioDTO::from)
                .collect(Collectors.toSet());
    }

    public Set<ItinerarioDTO> getItinerariosFavoritos(Long id_usuario){
        Usuario usuario = repositorioUsuario.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        return usuario.getFavoritos()
                .stream()
                .map(ItinerarioDTO::from)
                .collect(Collectors.toSet());
    }

    @Transactional
    public ItinerarioDTO agregarActividadItinerario(
            Long idItinerario,
            Long idActividad,
            LocalDate fecha,
            LocalTime hora) {

        Itinerario itinerario = repositorioItinerario.findById(idItinerario)
                .orElseThrow(() -> new RuntimeException("No se encontró el itinerario"));

        Actividad actividad = repositorioActividad.findById(idActividad)
                .orElseThrow(() -> new RuntimeException("No se encontró la actividad"));

        ItemItinerario itemItinerario = new ItemItinerario();
        itemItinerario.setItinerario(itinerario);
        itemItinerario.setActividad(actividad);
        itemItinerario.setFecha(fecha);
        itemItinerario.setHora(hora);

        itinerario.getItemItinerarios().add(itemItinerario);

        // Guardar el itinerario. Gracias a CascadeType.ALL en @OneToMany,
        // el ItemItinerario se guarda automáticamente.
        Itinerario guardado = repositorioItinerario.save(itinerario);

        return ItinerarioDTO.from(guardado);
    }

    @Transactional
    public ItinerarioDTO eliminarActividadItinerario(Long idItinerario, Long idItem) {
        Itinerario itinerario = repositorioItinerario.findById(idItinerario)
                .orElseThrow(() -> new RuntimeException("No se encontró el itinerario"));

        boolean removido = itinerario.getItemItinerarios()
                .removeIf(item -> item.getId().equals(idItem));

        if (!removido) {
            throw new RuntimeException("El item no pertenece a este itinerario");
        }

        Itinerario guardado = repositorioItinerario.save(itinerario);
        return ItinerarioDTO.from(guardado);
    }
}

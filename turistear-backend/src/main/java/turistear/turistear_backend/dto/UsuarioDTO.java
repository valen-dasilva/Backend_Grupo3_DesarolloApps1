package turistear.turistear_backend.dto;

import turistear.turistear_backend.enumerable.TipoTema;
import turistear.turistear_backend.model.Itinerario;
import turistear.turistear_backend.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UsuarioDTO(
        Long idUsuario,
        String nombre,
        String email,
        String contrasenia,
        LocalDate fechaNacimiento,
        TipoTema tema
) {

    public static UsuarioDTO from(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getContrasenia(),
                usuario.getFechaNacimiento(),
                usuario.getTema()

        );
    }
}

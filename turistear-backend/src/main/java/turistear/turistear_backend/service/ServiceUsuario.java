package turistear.turistear_backend.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import turistear.turistear_backend.dto.ItinerarioDTO;
import turistear.turistear_backend.dto.UsuarioDTO;
import turistear.turistear_backend.enumerable.TipoTema;
import turistear.turistear_backend.model.Usuario;
import turistear.turistear_backend.repository.ItinerarioRepository;
import turistear.turistear_backend.repository.UsuarioRepository;

@Service
public class ServiceUsuario {

    private final UsuarioRepository repositorioUsuario;
    private final ItinerarioRepository repositorioItinerario;

    public ServiceUsuario(UsuarioRepository repositorioUsuario,
                          ItinerarioRepository itinerarioRepository) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioItinerario = itinerarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    public void eliminarUsuario(Long idUsuario){
        repositorioUsuario.deleteById(idUsuario);
    }

    @Transactional
    public UsuarioDTO modificarEmail(Long idUsuario, String nuevoEmail) {
        Usuario usuario = repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEmail(nuevoEmail);
        repositorioUsuario.save(usuario);
        return UsuarioDTO.from(usuario);
    }

    @Transactional
    public UsuarioDTO modificarContrasenia(Long idUsuario, String nuevaContrasenia) {
        Usuario usuario = repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setContrasenia(nuevaContrasenia);
        repositorioUsuario.save(usuario);
        return UsuarioDTO.from(usuario);
    }

    @Transactional
    public UsuarioDTO modificarTema(Long idUsuario, TipoTema nuevoTema) {
        Usuario usuario = repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setTema(nuevoTema);
        repositorioUsuario.save(usuario);
        return UsuarioDTO.from(usuario);
    }
}

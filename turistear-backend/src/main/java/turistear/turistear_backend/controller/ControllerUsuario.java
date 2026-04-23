package turistear.turistear_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import turistear.turistear_backend.dto.UsuarioDTO;
import turistear.turistear_backend.enumerable.TipoTema;
import turistear.turistear_backend.model.Usuario;
import turistear.turistear_backend.service.ServiceUsuario;

@RestController
@RequestMapping("/users")
public class ControllerUsuario {

    private final ServiceUsuario serviceUsuario;

    public ControllerUsuario(ServiceUsuario serviceUsuario){
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return serviceUsuario.crearUsuario(usuario);
    }

    @DeleteMapping("/usuarios")
    public void eliminarUsuario(@RequestParam Long idUsuario) {
        serviceUsuario.eliminarUsuario(idUsuario);
    }

    @PatchMapping("/{idUsuario}/email")
    public UsuarioDTO modificarEmail(
            @PathVariable Long idUsuario,
            @RequestParam String nuevoEmail) {
        return serviceUsuario.modificarEmail(idUsuario, nuevoEmail);
    }

    @PatchMapping("/{idUsuario}/contrasenia")
    public UsuarioDTO modificarContrasenia(
            @PathVariable Long idUsuario,
            @RequestParam String nuevaContrasenia) {
        return serviceUsuario.modificarContrasenia(idUsuario, nuevaContrasenia);
    }

    @PatchMapping("/{idUsuario}/tema")
    public UsuarioDTO modificarTema(
            @PathVariable Long idUsuario,
            @RequestParam TipoTema nuevoTema) {
        return serviceUsuario.modificarTema(idUsuario, nuevoTema);
    }


}

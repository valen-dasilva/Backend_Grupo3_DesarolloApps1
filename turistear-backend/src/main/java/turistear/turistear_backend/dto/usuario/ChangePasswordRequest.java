package turistear.turistear_backend.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @Schema(example = "miContrasenaActual")
    @NotBlank(message = "La contraseña actual es obligatoria")
    private String contraseniaActual;

    @Schema(example = "miNuevaContrasena123")
    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 6, message = "La nueva contraseña debe tener al menos 6 caracteres")
    private String contraseniaNueva;
}
package turistear.turistear_backend.dto.favoritos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;

public record CrearItinerarioCompletoRequest(

    @Valid
    @NotNull(message = "Los datos del itinerario son obligatorios")
    CrearItinerarioRequest itinerario,

    @Valid
    @Size(max = 50, message = "No se pueden agregar más de 50 actividades de golpe")
    List<ItemFavoritoRequest> items

) {

    

}

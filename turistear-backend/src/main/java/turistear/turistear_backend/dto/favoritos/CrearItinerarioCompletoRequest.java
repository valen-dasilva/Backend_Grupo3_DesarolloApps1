package turistear.turistear_backend.dto.favoritos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CrearItinerarioCompletoRequest(

    @Valid
    @NotNull(message = "Los datos del itinerario son obligatorios")
    CrearItinerarioRequest itinerario,

    @Valid
    List<ItemFavoritoRequest> items

) {

    

}

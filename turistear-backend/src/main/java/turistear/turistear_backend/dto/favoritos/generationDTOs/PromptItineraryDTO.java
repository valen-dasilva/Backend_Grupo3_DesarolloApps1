package turistear.turistear_backend.dto.favoritos.generationDTOs;

import turistear.turistear_backend.enumerable.CategoriaItinerario;
import turistear.turistear_backend.enumerable.Provincia;

import java.util.Date;
import java.util.List;

public record PromptItineraryDTO(
        String descripcion,
        Provincia provincia,
        Date fecha_inicio,
        Date fecha_final,
        List<CategoriaItinerario> categorias
) {
}

package turistear.turistear_backend.service.generationAPI;

import turistear.turistear_backend.dto.favoritos.generationDTOs.PromptItineraryDTO;
import turistear.turistear_backend.model.ItinerarioUsuario;


import java.util.List;

public interface GenAPIInterface {

    List<ItinerarioUsuario> generateItineraries(PromptItineraryDTO prompt);
}

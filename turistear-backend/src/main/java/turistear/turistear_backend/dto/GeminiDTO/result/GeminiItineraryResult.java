package turistear.turistear_backend.dto.GeminiDTO.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiItineraryResult(String titulo, String descripcion, List<GeminiItemResult> items) {}

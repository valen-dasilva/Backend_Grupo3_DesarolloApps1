package turistear.turistear_backend.dto.GeminiDTO.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiItemResult(Integer dia, String hora, GeminiActividadResult actividad) {}

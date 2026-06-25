package turistear.turistear_backend.dto.GeminiDTO.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiActividadResult(String nombre, String descripcion, String localidad, String direccion) {}

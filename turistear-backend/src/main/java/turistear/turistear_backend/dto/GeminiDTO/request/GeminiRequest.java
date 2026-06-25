package turistear.turistear_backend.dto.GeminiDTO.request;

import java.util.List;

public record GeminiRequest(List<Content> contents, GenerationConfig generationConfig, SystemInstruction systemInstruction) {
    public record Content(List<Part> parts) {}
    public record Part(String text) {}
    public record GenerationConfig(String responseMimeType) {}
    public record SystemInstruction(List<Part> parts) {}
}

package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record VideoPublishDTO(@NotEmpty String fileId, @NotEmpty String title, String description,
                              @NotEmpty List<String> categoryNames) implements VideoPublishPort {
}

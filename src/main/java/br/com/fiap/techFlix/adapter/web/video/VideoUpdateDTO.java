package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.VideoUpdatePort;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record VideoUpdateDTO(@NotEmpty String title, String description,
                             @NotEmpty List<String> categoryNames) implements VideoUpdatePort {
}

package br.com.fiap.techflix.adapter.web.video;

import br.com.fiap.techflix.application.ports.VideoUpdatePort;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record VideoUpdateDTO(@NotEmpty String title, String description,
                             @NotEmpty List<String> categoryNames) implements VideoUpdatePort {
}

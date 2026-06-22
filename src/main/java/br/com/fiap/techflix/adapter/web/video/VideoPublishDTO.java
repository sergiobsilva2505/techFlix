package br.com.fiap.techflix.adapter.web.video;

import br.com.fiap.techflix.application.ports.VideoPublishPort;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record VideoPublishDTO(@NotEmpty String fileId, @NotEmpty String title, String description,
                              @NotEmpty List<String> categoryNames) implements VideoPublishPort {
}

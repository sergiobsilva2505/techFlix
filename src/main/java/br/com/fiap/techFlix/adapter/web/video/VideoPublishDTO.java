package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import jakarta.validation.constraints.NotEmpty;

public record VideoPublishDTO(@NotEmpty String title, String description, @NotEmpty String categoryName) implements VideoPublishPort {}

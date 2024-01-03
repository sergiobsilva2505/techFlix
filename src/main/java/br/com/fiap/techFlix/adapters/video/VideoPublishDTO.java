package br.com.fiap.techFlix.adapters.video;

import jakarta.validation.constraints.NotEmpty;

public record VideoPublishDTO(@NotEmpty String title, String description, @NotEmpty String categoryName) {}

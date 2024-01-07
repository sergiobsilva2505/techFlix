package br.com.fiap.techFlix.infrastructure.controllers;

import jakarta.validation.constraints.NotEmpty;

public record VideoPublishDTO(@NotEmpty String title, String description, @NotEmpty String categoryName) {}

package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.domain.entities.Video;

public record BookmarkVideoCreateDTO(User user, Video video, boolean bookmark) {
}

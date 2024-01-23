package br.com.fiap.techFlix.adapter.web.video;

import java.net.URI;
import java.time.LocalDateTime;

public record VideoShowDTO(String id, String title, String description, String categoryName,
                           URI uri, LocalDateTime publicationDate) {
}

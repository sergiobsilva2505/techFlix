package br.com.fiap.techFlix.adapter.web.video;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

public record VideoShowDTO(String id, String title, String description, List<String> categories, int likes, int views,
                           URI uri, LocalDateTime publicationDate) {
}

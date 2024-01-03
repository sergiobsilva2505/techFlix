package br.com.fiap.techFlix.adapters.video;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public record VideoShowDTO(String id, String title, String description, String categoryName,
                           LocalDateTime publicationDate) {
    public VideoShowDTO(VideoMapper videoMapper) {
        this(videoMapper.getId(), videoMapper.getTitle(), videoMapper.getDescription(), videoMapper.getCategoryName(), videoMapper.getPublicationDate());
    }

    public URI url() {
        try {
            return new URI(id);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error creating URI");
        }
    }
}

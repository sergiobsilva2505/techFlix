package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryDocument;
import br.com.fiap.techFlix.infrastructure.persistence.VideoDocument;

public class VideoMapper {

    public static Video toDomain(VideoDocument videoDocument) {
        Category category = CategoryMapper.toDomain(videoDocument.getCategory());
        return new Video(videoDocument.getId(), videoDocument.getTitle(), videoDocument.getDescription(), category, videoDocument.getPublicationDate());
    }

    public static VideoDocument toPersistence(Video video) {
        CategoryDocument categoryDocument = CategoryMapper.toPersistence(video.getCategory());
        return new VideoDocument(video.getTitle(), video.getDescription(), categoryDocument, video.getPublicationDate());
    }
}

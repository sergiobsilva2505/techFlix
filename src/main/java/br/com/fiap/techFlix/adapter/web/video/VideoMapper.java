package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import br.com.fiap.techFlix.adapter.web.category.CategoryMapper;
import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techFlix.adapter.persistence.video.VideoDocument;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VideoMapper {

    public static Video toDomain(VideoDocument videoDocument) {
        Category category = CategoryMapper.toDomain(videoDocument.getCategory());
        return new Video(videoDocument.getId(), videoDocument.getTitle(), videoDocument.getDescription(), category, videoDocument.getPublicationDate());
    }

    public static VideoDocument toPersistence(Video video) {
        CategoryDocument categoryDocument = CategoryMapper.toPersistence(video.getCategory());
        return new VideoDocument(video.getId(), video.getTitle(), video.getDescription(), categoryDocument, video.getPublicationDate());
    }

    public static VideoDocument toPersistence(VideoPublishPort videoPublishPort, CategoryDocument categoryDocument, LocalDateTime publicationDate) {
        return new VideoDocument(null, videoPublishPort.title(), videoPublishPort.description(), categoryDocument, publicationDate);
    }

    public static VideoShowDTO toView(Video video) {
        return new VideoShowDTO(video.getId(), video.getTitle(), video.getDescription(), video.getCategory().getName(), video.getUrl(), video.getPublicationDate());
    }
}

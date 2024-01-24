package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techFlix.adapter.persistence.video.VideoDocument;
import br.com.fiap.techFlix.adapter.web.category.CategoryMapper;
import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;

import java.time.LocalDateTime;
import java.util.List;

public class VideoMapper {

    public static Video toDomain(VideoDocument videoDocument) {
        List<Category> categories = videoDocument.getCategories().stream().map(CategoryMapper::toDomain).toList();
        return new Video(videoDocument.getId(), videoDocument.getTitle(), videoDocument.getDescription(), categories, videoDocument.getPublicationDate());
    }

    public static VideoDocument toPersistence(Video video) {
        List<CategoryDocument> categoryDocuments = video.getCategories().stream().map(CategoryMapper::toPersistence).toList();
        return new VideoDocument(video.getId(), video.getTitle(), video.getDescription(), categoryDocuments, video.getPublicationDate());
    }

    public static VideoDocument toPersistence(VideoPublishPort videoPublishPort, List<CategoryDocument> categoryDocuments, LocalDateTime publicationDate) {
        return new VideoDocument(videoPublishPort.fileId(), videoPublishPort.title(), videoPublishPort.description(), categoryDocuments, publicationDate);
    }

    public static VideoShowDTO toView(Video video) {
        List<String> categories = video.getCategories().stream().map(Category::getName).toList();
        return new VideoShowDTO(video.getId(), video.getTitle(), video.getDescription(), categories, video.getUrl(), video.getPublicationDate());
    }
}

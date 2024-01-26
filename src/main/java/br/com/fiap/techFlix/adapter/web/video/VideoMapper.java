package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techFlix.adapter.persistence.video.VideoDetailsDocument;
import br.com.fiap.techFlix.adapter.persistence.video.VideoDocument;
import br.com.fiap.techFlix.adapter.web.category.CategoryMapper;
import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import br.com.fiap.techFlix.domain.entities.video.VideoDetails;

import java.time.LocalDateTime;
import java.util.List;

public class VideoMapper {

    public static Video toDomain(VideoDocument videoDocument) {
        List<Category> categories = videoDocument.getCategories().stream().map(CategoryMapper::toDomain).toList();
        VideoDetails videoDetails = new VideoDetails(videoDocument.getLikes(), videoDocument.getViews());
        return new Video(videoDocument.getId(), videoDocument.getTitle(), videoDocument.getDescription(), categories, videoDetails, videoDocument.getPublicationDate());
    }

    public static VideoDocument toPersistence(Video video) {
        List<CategoryDocument> categoryDocuments = video.getCategories().stream().map(CategoryMapper::toPersistence).toList();
        VideoDetailsDocument videoDetailsDocument = new VideoDetailsDocument(video.getLikes(), video.getViews());
        return new VideoDocument(video.getId(), video.getTitle(), video.getDescription(), categoryDocuments, videoDetailsDocument, video.getPublicationDate());
    }

    public static VideoDocument toPersistence(VideoPublishPort videoPublishPort, List<CategoryDocument> categoryDocuments, LocalDateTime publicationDate) {
        VideoDetailsDocument videoDetailsDocument = new VideoDetailsDocument(0, 0);
        return new VideoDocument(videoPublishPort.fileId(), videoPublishPort.title(), videoPublishPort.description(), categoryDocuments, videoDetailsDocument, publicationDate);
    }

    public static VideoShowDTO toView(Video video) {
        List<String> categories = video.getCategories().stream().map(Category::getName).toList();
        return new VideoShowDTO(video.getId(), video.getTitle(), video.getDescription(), categories, video.getLikes(), video.getViews(), video.getUrl(), video.getPublicationDate());
    }
}

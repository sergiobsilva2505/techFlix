package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.controllers.VideoPublishDTO;

import java.time.LocalDateTime;

public class PublishVideoUseCase {

    private final CategoryGateway categoryGateway;
    private final VideoGateway videoGateway;

    public PublishVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        this.categoryGateway = categoryGateway;
        this.videoGateway = videoGateway;
    }

    public Video publishVideo(VideoPublishDTO videoPublishDTO) {
        Category category = categoryGateway.findByName(videoPublishDTO.categoryName()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        LocalDateTime now = LocalDateTime.now();
        Video video = new Video(videoPublishDTO.title(), videoPublishDTO.description(), new Category(category.getName()), now);

        return videoGateway.save(video);
    }
}

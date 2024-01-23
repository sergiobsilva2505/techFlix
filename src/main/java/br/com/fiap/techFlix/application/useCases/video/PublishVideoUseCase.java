package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;

import java.time.LocalDateTime;

public class PublishVideoUseCase {

    private final CategoryGateway categoryGateway;
    private final VideoGateway videoGateway;

    public PublishVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        this.categoryGateway = categoryGateway;
        this.videoGateway = videoGateway;
    }

    public Video publishVideo(VideoPublishPort videoPublishPort) {
        Category category = categoryGateway.findByName(videoPublishPort.categoryName()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        LocalDateTime now = LocalDateTime.now();

        return videoGateway.save(videoPublishPort, category, now);
    }
}


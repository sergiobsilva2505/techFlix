package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;

import java.time.LocalDateTime;
import java.util.List;

public class PublishVideoUseCase {

    private final CategoryGateway categoryGateway;
    private final VideoGateway videoGateway;

    public PublishVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        this.categoryGateway = categoryGateway;
        this.videoGateway = videoGateway;
    }

    public Video publishVideo(VideoPublishPort videoPublishPort) {
        List<Category> categories = categoryGateway.findAllByNameIn(videoPublishPort.categoryNames());

        if (categories.size() != videoPublishPort.categoryNames().size()) {
            throw new IllegalArgumentException("Category not found");
        }

        LocalDateTime now = LocalDateTime.now();

        return videoGateway.save(videoPublishPort, categories, now);
    }
}


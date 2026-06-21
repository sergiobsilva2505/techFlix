package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.category.CategoryGateway;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.application.ports.VideoPublishPort;
import br.com.fiap.techflix.domain.entities.category.Category;
import br.com.fiap.techflix.domain.entities.video.Video;

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


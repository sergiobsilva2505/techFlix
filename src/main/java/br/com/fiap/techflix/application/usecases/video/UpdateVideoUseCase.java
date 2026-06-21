package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.category.CategoryGateway;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.application.ports.VideoUpdatePort;
import br.com.fiap.techflix.domain.entities.category.Category;
import br.com.fiap.techflix.domain.entities.video.Video;

import java.util.List;

public class UpdateVideoUseCase {

    private final CategoryGateway categoryGateway;
    private final VideoGateway videoGateway;

    public UpdateVideoUseCase(CategoryGateway categoryGateway, VideoGateway videoGateway) {
        this.categoryGateway = categoryGateway;
        this.videoGateway = videoGateway;
    }

    public Video updateVideo(String id, VideoUpdatePort videoUpdatePort) {
        Video video = videoGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("Video not found"));

        List<Category> categories = categoryGateway.findAllByNameIn(videoUpdatePort.categoryNames());

        if (categories.size() != videoUpdatePort.categoryNames().size()) {
            throw new IllegalArgumentException("Category not found");
        }

        return videoGateway.update(video, videoUpdatePort, categories);
    }
}

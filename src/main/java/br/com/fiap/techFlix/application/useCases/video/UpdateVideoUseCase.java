package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.VideoUpdatePort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;

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

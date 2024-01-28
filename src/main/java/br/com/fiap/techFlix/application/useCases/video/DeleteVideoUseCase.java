package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;

public class DeleteVideoUseCase {

    private final VideoGateway videoGateway;

    public DeleteVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public void deleteVideo(String id) {
        videoGateway.deleteVideo(id);
    }
}

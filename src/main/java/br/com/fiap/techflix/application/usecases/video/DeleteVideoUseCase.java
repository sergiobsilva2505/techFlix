package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.video.VideoGateway;

public class DeleteVideoUseCase {

    private final VideoGateway videoGateway;

    public DeleteVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public void deleteVideo(String id) {
        videoGateway.deleteVideo(id);
    }
}

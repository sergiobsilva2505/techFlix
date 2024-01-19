package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.Video;

public class ListVideoUseCase {

    private final VideoGateway videoGateway;

    public ListVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public PagePort<Video> listVideos(int page, int size) {
        return videoGateway.findAll(page, size);
    }

    public Video listVideo(String id) {
        return videoGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("Video not found"));
    }
}

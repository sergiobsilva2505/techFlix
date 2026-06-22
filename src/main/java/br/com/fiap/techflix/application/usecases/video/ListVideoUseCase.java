package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.domain.entities.video.Video;

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

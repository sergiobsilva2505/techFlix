package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.ports.VideoSearchPort;
import br.com.fiap.techFlix.domain.entities.video.Video;

public class SearchVideoUseCase {

    private final VideoGateway videoGateway;

    public SearchVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public PagePort<Video> searchVideos(VideoSearchPort videoSearchPort) {
        return videoGateway.searchVideos(videoSearchPort);
    }
}

package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.application.ports.VideoSearchPort;
import br.com.fiap.techflix.domain.entities.video.Video;

public class SearchVideoUseCase {

    private final VideoGateway videoGateway;

    public SearchVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public PagePort<Video> searchVideos(VideoSearchPort videoSearchPort) {
        return videoGateway.searchVideos(videoSearchPort);
    }
}

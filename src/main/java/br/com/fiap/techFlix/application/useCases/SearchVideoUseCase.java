package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.controllers.SearchVideoDTO;

public class SearchVideoUseCase {

    private final VideoGateway videoGateway;

    public SearchVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public PagePort<Video> searchVideos(SearchVideoDTO searchVideoDTO) {
        return videoGateway.searchVideos(searchVideoDTO);
    }
}

package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.domain.entities.video.Video;
import br.com.fiap.techFlix.adapter.web.video.SearchVideoDTO;

public class SearchVideoUseCase {

    private final VideoGateway videoGateway;

    public SearchVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public PagePort<Video> searchVideos(SearchVideoDTO searchVideoDTO) {
        return videoGateway.searchVideos(searchVideoDTO);
    }
}

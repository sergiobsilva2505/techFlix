package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.Video;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class ListVideoUseCase {

    private final VideoGateway videoGateway;

    public ListVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public List<Video> listVideos(PageRequest pageRequest) {
        return videoGateway.findAll();
    }

    public Video listVideo(String id) {
        return videoGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("Video not found"));
    }

}

package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.infrastructure.controllers.VideoShowDTO;
import br.com.fiap.techFlix.infrastructure.persistence.VideoDocument;
import br.com.fiap.techFlix.infrastructure.persistence.VideoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ListVideoUseCase {

    private final VideoRepository videoRepository;

    public ListVideoUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Flux<VideoShowDTO> listVideosUseCase(PageRequest pageRequest) {
        Flux<VideoDocument> videos = videoRepository.findAll();

        return videos.map(VideoShowDTO::new);
    }

    public Mono<VideoShowDTO> listVideoUseCase(String id) {
        Mono<VideoDocument> videoMapper = videoRepository.findById(id);

        return videoMapper.map(VideoShowDTO::new);
    }

}

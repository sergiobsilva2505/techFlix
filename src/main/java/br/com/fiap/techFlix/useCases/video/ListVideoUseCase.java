package br.com.fiap.techFlix.useCases.video;

import br.com.fiap.techFlix.adapters.video.*;
import org.springframework.data.domain.Page;
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
        Flux<VideoMapper> videos = videoRepository.findAll();

        return videos.map(VideoShowDTO::new);
    }

    public Mono<VideoShowDTO> listVideoUseCase(String id) {
        Mono<VideoMapper> videoMapper = videoRepository.findById(id);

        return videoMapper.map(VideoShowDTO::new);
    }

}

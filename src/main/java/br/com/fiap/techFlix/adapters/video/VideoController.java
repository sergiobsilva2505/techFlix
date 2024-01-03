package br.com.fiap.techFlix.adapters.video;

import br.com.fiap.techFlix.entities.video.Video;
import br.com.fiap.techFlix.useCases.video.ListVideoUseCase;
import br.com.fiap.techFlix.useCases.video.PublishVideoUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class VideoController {

    private final ListVideoUseCase listVideoUseCase;
    private final PublishVideoUseCase publishVideoUseCase;

    public VideoController(ListVideoUseCase listVideoUseCase, PublishVideoUseCase publishVideoUseCase) {
        this.listVideoUseCase = listVideoUseCase;
        this.publishVideoUseCase = publishVideoUseCase;
    }

    @PostMapping("/videos")
    public Mono<String> publishVideo(@Valid @RequestBody VideoPublishDTO videoPublishDTO) {
        Mono<Video> video = publishVideoUseCase.publishVideo(videoPublishDTO);

        return video.map(Video::getId).map(id -> "/videos/" + id);
    }

    @GetMapping("/videos")
    public Flux<VideoShowDTO> getVideoById(@PageableDefault PageRequest pageRequest) {
        return listVideoUseCase.listVideosUseCase(pageRequest);
    }

    @GetMapping("/videos/{id}")
    public Mono<VideoShowDTO> getVideoById(@PathVariable String id) {
        return listVideoUseCase.listVideoUseCase(id);
    }
}

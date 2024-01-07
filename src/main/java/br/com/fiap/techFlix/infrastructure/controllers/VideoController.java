package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.application.useCases.ListVideoUseCase;
import br.com.fiap.techFlix.application.useCases.PublishVideoUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> publishVideo(@Valid @RequestBody VideoPublishDTO videoPublishDTO) {
        Video video = publishVideoUseCase.publishVideo(videoPublishDTO);

        URI uri = URI.create("/videos/" + video.getId());
        return ResponseEntity.created(uri).build();
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

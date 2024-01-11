package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.useCases.ListVideoUseCase;
import br.com.fiap.techFlix.application.useCases.PublishVideoUseCase;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.gateways.VideoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public PagePort<VideoShowDTO> getVideoById(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return listVideoUseCase.listVideos(page, size).map(VideoMapper::toView);
    }

    @GetMapping("/videos/{id}")
    public VideoShowDTO getVideoById(@PathVariable String id) {
        return VideoMapper.toView(listVideoUseCase.listVideo(id));
    }
}

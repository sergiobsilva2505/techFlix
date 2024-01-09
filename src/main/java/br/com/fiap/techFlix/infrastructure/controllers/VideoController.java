package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.useCases.ListVideoUseCase;
import br.com.fiap.techFlix.application.useCases.PublishVideoUseCase;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.gateways.VideoMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public List<VideoShowDTO> getVideoById(@PageableDefault PageRequest pageRequest) {
        return listVideoUseCase.listVideos(pageRequest).stream().map(VideoMapper::toView).toList();
    }

    @GetMapping("/videos/{id}")
    public VideoShowDTO getVideoById(@PathVariable String id) {
        return VideoMapper.toView(listVideoUseCase.listVideo(id));
    }

    @GetMapping("/videos/play/{id}")
    public VideoShowDTO playVideo(@PathVariable String id) {
        return VideoMapper.toView(listVideoUseCase.listVideo(id));
    }
}

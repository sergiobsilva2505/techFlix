package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.gateways.FileGateway;
import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.useCases.ListVideoUseCase;
import br.com.fiap.techFlix.application.useCases.PublishVideoUseCase;
import br.com.fiap.techFlix.domain.entities.Video;
import br.com.fiap.techFlix.infrastructure.gateways.FileMapper;
import br.com.fiap.techFlix.infrastructure.gateways.VideoMapper;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class VideoController {

    private final FileGateway fileGateway;
    private final ListVideoUseCase listVideoUseCase;
    private final PublishVideoUseCase publishVideoUseCase;

    public VideoController(FileGateway fileGateway, ListVideoUseCase listVideoUseCase, PublishVideoUseCase publishVideoUseCase) {
        this.fileGateway = fileGateway;
        this.listVideoUseCase = listVideoUseCase;
        this.publishVideoUseCase = publishVideoUseCase;
    }

    @PostMapping("/videos")
    public ResponseEntity<String> publishVideo(@Valid @RequestBody VideoPublishDTO videoPublishDTO) {
        Video video = publishVideoUseCase.publishVideo(videoPublishDTO);

        URI uri = URI.create("/videos/" + video.getId());
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/videos/upload")
    public Mono<FileShowDTO> fileUpload(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("id: " + id);
        return fileGateway.saveAttachment(file, id).map(FileMapper::toView);
    }

    @GetMapping(value = "/videos/play/{id}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String id, @RequestHeader("Range") String range) {
        return fileGateway.findById(id).map(ByteArrayResource::new);
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

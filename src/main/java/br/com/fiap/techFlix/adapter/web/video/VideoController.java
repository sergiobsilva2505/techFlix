package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.gateways.file.FileGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.useCases.video.*;
import br.com.fiap.techFlix.domain.entities.video.Video;
import br.com.fiap.techFlix.adapter.web.file.FileShowDTO;
import br.com.fiap.techFlix.adapter.web.file.FileMapper;
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
    private final SearchVideoUseCase searchVideoUseCase;
    private final WatchVideoUseCase watchVideoUseCase;

    public VideoController(FileGateway fileGateway, ListVideoUseCase listVideoUseCase, PublishVideoUseCase publishVideoUseCase, SearchVideoUseCase searchVideoUseCase, WatchVideoUseCase watchVideoUseCase) {
        this.fileGateway = fileGateway;
        this.listVideoUseCase = listVideoUseCase;
        this.publishVideoUseCase = publishVideoUseCase;
        this.searchVideoUseCase = searchVideoUseCase;
        this.watchVideoUseCase = watchVideoUseCase;
    }

    @PostMapping("/videos")
    public ResponseEntity<String> publishVideo(@Valid @RequestBody VideoPublishDTO videoPublishDTO) {
        Video video = publishVideoUseCase.publishVideo(videoPublishDTO);

        URI uri = URI.create("/videos/" + video.getId());
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/videos/upload")
    public Mono<FileShowDTO> fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return fileGateway.saveAttachment(file).map(FileMapper::toView);
    }

    @GetMapping(value = "/videos/play/{id}", produces = "video/mp4")
    public Mono<Resource> playVideo(@PathVariable String id, @RequestHeader("Range") String range) {
        watchVideoUseCase.watchVideo(id);
        return fileGateway.findById(id).map(ByteArrayResource::new);
    }

    @GetMapping("/videos")
    public PagePort<VideoShowDTO> searchVideos(VideoSearchDTO searchVideoDTO) {
        return searchVideoUseCase.searchVideos(searchVideoDTO).map(VideoMapper::toView);
    }

    @GetMapping("/videos/{id}")
    public VideoShowDTO getVideoById(@PathVariable String id) {
        return VideoMapper.toView(listVideoUseCase.listVideo(id));
    }
}

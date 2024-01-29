package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.adapter.web.file.FileMapper;
import br.com.fiap.techFlix.adapter.web.file.FileShowDTO;
import br.com.fiap.techFlix.application.gateways.file.FileGateway;
import br.com.fiap.techFlix.application.useCases.video.WatchVideoUseCase;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
public class VideoFileController {

    private final FileGateway fileGateway;
    private final WatchVideoUseCase watchVideoUseCase;

    public VideoFileController(FileGateway fileGateway, WatchVideoUseCase watchVideoUseCase) {
        this.fileGateway = fileGateway;
        this.watchVideoUseCase = watchVideoUseCase;
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
}

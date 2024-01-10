package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.gateways.FileGateway;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
public class VideoFileController {

    private final FileGateway fileUploadGateway;

    public VideoFileController(FileGateway fileUploadGateway) {
        this.fileUploadGateway = fileUploadGateway;
    }

    @PostMapping("/single/upload")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("hi");
        fileUploadGateway.saveAttachment(file, "659e0dcdca671e438e3de375");

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/videos/play/{id}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String id, @RequestHeader("Range") String range) {
        System.out.println(range);

        return fileUploadGateway.findById(id).map(ByteArrayResource::new);
    }
}

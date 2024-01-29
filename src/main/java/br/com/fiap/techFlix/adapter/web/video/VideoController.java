package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.useCases.video.*;
import br.com.fiap.techFlix.domain.entities.video.Video;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class VideoController {

    private final DeleteVideoUseCase deleteVideoUseCase;
    private final ListVideoUseCase listVideoUseCase;
    private final PublishVideoUseCase publishVideoUseCase;
    private final SearchVideoUseCase searchVideoUseCase;
    private final UpdateVideoUseCase updateVideoUseCase;
    private final VideoRecommendationsUseCase videoRecommendationsUseCase;

    public VideoController(DeleteVideoUseCase deleteVideoUseCase, ListVideoUseCase listVideoUseCase, PublishVideoUseCase publishVideoUseCase, SearchVideoUseCase searchVideoUseCase, UpdateVideoUseCase updateVideoUseCase, VideoRecommendationsUseCase videoRecommendationsUseCase) {
        this.deleteVideoUseCase = deleteVideoUseCase;
        this.listVideoUseCase = listVideoUseCase;
        this.publishVideoUseCase = publishVideoUseCase;
        this.searchVideoUseCase = searchVideoUseCase;
        this.updateVideoUseCase = updateVideoUseCase;
        this.videoRecommendationsUseCase = videoRecommendationsUseCase;
    }

    @PostMapping("/videos")
    public ResponseEntity<String> publishVideo(@Valid @RequestBody VideoPublishDTO videoPublishDTO) {
        Video video = publishVideoUseCase.publishVideo(videoPublishDTO);

        URI uri = URI.create("/videos/" + video.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/videos")
    public PagePort<VideoShowDTO> searchVideos(VideoSearchDTO searchVideoDTO) {
        return searchVideoUseCase.searchVideos(searchVideoDTO).map(VideoMapper::toView);
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<VideoShowDTO> getVideoById(@PathVariable String id) {
        Video video = listVideoUseCase.listVideo(id);
        return ResponseEntity.ok(VideoMapper.toView(video));
    }

    @PutMapping("/videos/{id}")
    public ResponseEntity<VideoShowDTO> updateVideo(@PathVariable String id, @Valid @RequestBody VideoUpdateDTO videoUpdateDTO) {
        Video video = updateVideoUseCase.updateVideo(id, videoUpdateDTO);
        return ResponseEntity.ok(VideoMapper.toView(video));
    }

    @DeleteMapping("/videos/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String id) {
        deleteVideoUseCase.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/videos/{userId}/recommendations")
    public List<VideoShowDTO> getRecommendations(@PathVariable String userId) {
        return videoRecommendationsUseCase.getRecommendations(userId).stream().map(VideoMapper::toView).toList();
    }
}

package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.VideoStatisticsPort;
import br.com.fiap.techFlix.application.useCases.video.VideoStatisticsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoStatisticsController {

    private final VideoStatisticsUseCase videoStatisticsUseCase;

    public VideoStatisticsController(VideoStatisticsUseCase videoStatisticsUseCase) {
        this.videoStatisticsUseCase = videoStatisticsUseCase;
    }

    @GetMapping("/videos/statistics")
    public ResponseEntity<VideoStatisticsPort> getStatistics() {
        VideoStatisticsPort overallStatistics = videoStatisticsUseCase.getOverallStatistics();
        return ResponseEntity.ok(overallStatistics);
    }
}

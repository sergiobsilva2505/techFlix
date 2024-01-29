package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.VideoStatisticsPort;

public class VideoStatisticsUseCase {

    private final VideoGateway videoGateway;

    public VideoStatisticsUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public VideoStatisticsPort getOverallStatistics() {
        return videoGateway.getOverallStatistics();
    }
}

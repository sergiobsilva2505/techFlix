package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.application.ports.VideoStatisticsPort;

public class VideoStatisticsUseCase {

    private final VideoGateway videoGateway;

    public VideoStatisticsUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public VideoStatisticsPort getOverallStatistics() {
        return videoGateway.getOverallStatistics();
    }
}

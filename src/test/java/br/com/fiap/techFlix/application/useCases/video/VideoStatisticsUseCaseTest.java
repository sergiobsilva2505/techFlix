package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.adapter.web.video.VideoStatisticsDTO;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.VideoStatisticsPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VideoStatisticsUseCaseTest {

    private VideoGateway videoGateway;
    private VideoStatisticsUseCase videoStatisticsUseCase;

    @BeforeEach
    void setUp() {
        videoGateway = mock(VideoGateway.class);
        videoStatisticsUseCase = new VideoStatisticsUseCase(videoGateway);
    }

    @Test
    void shouldReturnStatisticsWhenDataIsAvailable() {
        VideoStatisticsPort videoStatisticsPort = new VideoStatisticsDTO(1, 1, 1);
        when(videoGateway.getOverallStatistics()).thenReturn(videoStatisticsPort);

        VideoStatisticsPort result = videoStatisticsUseCase.getOverallStatistics();

        assertNotNull(result);
    }
}
package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.application.ports.VideoStatisticsPort;
import br.com.fiap.techFlix.application.useCases.video.VideoStatisticsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class VideoStatisticsControllerTest {

    @Mock
    private VideoStatisticsUseCase videoStatisticsUseCase;

    @InjectMocks
    private VideoStatisticsController videoStatisticsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnStatisticsWhenGetStatisticsIsCalled() {
        VideoStatisticsPort videoStatisticsPort = new VideoStatisticsDTO(1, 2, 3);
        when(videoStatisticsUseCase.getOverallStatistics()).thenReturn(videoStatisticsPort);

        ResponseEntity<VideoStatisticsPort> response = videoStatisticsController.getStatistics();

        assertEquals(videoStatisticsPort, response.getBody());
    }
}
package br.com.fiap.techflix.adapter.web.video;

import br.com.fiap.techflix.application.ports.VideoStatisticsPort;
import br.com.fiap.techflix.application.usecases.video.VideoStatisticsUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    @DisplayName("Retorna estatísticas gerais dos vídeos")
    void shouldReturnStatisticsWhenGetStatisticsIsCalled() {
        VideoStatisticsPort videoStatisticsPort = new VideoStatisticsDTO(1, 2, 3);
        when(videoStatisticsUseCase.getOverallStatistics()).thenReturn(videoStatisticsPort);

        ResponseEntity<VideoStatisticsPort> response = videoStatisticsController.getStatistics();

        assertEquals(videoStatisticsPort, response.getBody());
    }
}
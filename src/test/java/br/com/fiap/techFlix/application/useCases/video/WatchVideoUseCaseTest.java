package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WatchVideoUseCaseTest {

    private VideoGateway videoGateway;
    private WatchVideoUseCase watchVideoUseCase;

    @BeforeEach
    void setUp() {
        videoGateway = mock(VideoGateway.class);
        watchVideoUseCase = new WatchVideoUseCase(videoGateway);
    }

    @Test
    void shouldWatchVideoWhenIdExists() {
        String id = "existingId";
        when(videoGateway.existsById(id)).thenReturn(true);

        watchVideoUseCase.watchVideo(id);

        verify(videoGateway).existsById(id);
        verify(videoGateway).watchVideo(id);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        String id = "nonExistingId";
        when(videoGateway.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> watchVideoUseCase.watchVideo(id));

        verify(videoGateway).existsById(id);
        verify(videoGateway, never()).watchVideo(anyString());
    }
}
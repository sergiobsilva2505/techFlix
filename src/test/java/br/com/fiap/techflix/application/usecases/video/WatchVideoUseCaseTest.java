package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("Registra visualização quando ID existe")
    void shouldWatchVideoWhenIdExists() {
        String id = "existingId";
        when(videoGateway.existsById(id)).thenReturn(true);

        watchVideoUseCase.watchVideo(id);

        verify(videoGateway).existsById(id);
        verify(videoGateway).watchVideo(id);
    }

    @Test
    @DisplayName("Lança exceção quando ID não existe")
    void shouldThrowExceptionWhenIdDoesNotExist() {
        String id = "nonExistingId";
        when(videoGateway.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> watchVideoUseCase.watchVideo(id));

        verify(videoGateway).existsById(id);
        verify(videoGateway, never()).watchVideo(anyString());
    }
}
package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteVideoUseCaseTest {

    private VideoGateway videoGateway;
    private DeleteVideoUseCase deleteVideoUseCase;

    @BeforeEach
    void setUp() {
        videoGateway = mock(VideoGateway.class);
        deleteVideoUseCase = new DeleteVideoUseCase(videoGateway);
    }

    @Test
    @DisplayName("Deleta vídeo pelo ID com sucesso")
    void shouldCallDeleteVideoOnVideoGatewayWhenDeleteVideoIsCalled() {
        String videoId = "videoId";
        deleteVideoUseCase.deleteVideo(videoId);
        verify(videoGateway).deleteVideo(videoId);
    }
}
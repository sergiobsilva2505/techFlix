package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import org.junit.jupiter.api.BeforeEach;
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
    void shouldCallDeleteVideoOnVideoGatewayWhenDeleteVideoIsCalled() {
        String videoId = "videoId";
        deleteVideoUseCase.deleteVideo(videoId);
        verify(videoGateway).deleteVideo(videoId);
    }
}
package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ListVideoUseCaseTest {

    private VideoGateway videoGateway;
    private ListVideoUseCase listVideoUseCase;

    @BeforeEach
    void setUp() {
        videoGateway = mock(VideoGateway.class);
        listVideoUseCase = new ListVideoUseCase(videoGateway);
    }

    @Test
    void shouldReturnPagePortWhenVideosExist() {
        PagePort<Video> pagePort = mock(PagePort.class);
        when(videoGateway.findAll(anyInt(), anyInt())).thenReturn(pagePort);

        PagePort<Video> result = listVideoUseCase.listVideos(1, 10);

        assertEquals(pagePort, result);
        verify(videoGateway).findAll(1, 10);
    }

    @Test
    void shouldReturnVideoWhenIdExists() {
        Video video = mock(Video.class);
        String id = "existingId";
        when(videoGateway.findById(id)).thenReturn(Optional.of(video));

        Video result = listVideoUseCase.listVideo(id);

        assertEquals(video, result);
        verify(videoGateway).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        String id = "nonExistingId";
        when(videoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> listVideoUseCase.listVideo(id));
    }
}
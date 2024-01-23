package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.useCases.video.ListVideoUseCase;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ListVideoUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void listVideosReturnsPagePort() {
        when(videoGateway.findAll(anyInt(), anyInt())).thenReturn(mock(PagePort.class));
        ListVideoUseCase useCase = new ListVideoUseCase(videoGateway);
        assertNotNull(useCase.listVideos(1, 10));
    }

    @Test
    void listVideoReturnsVideo() {
        when(videoGateway.findById(anyString())).thenReturn(Optional.of(mock(Video.class)));
        ListVideoUseCase useCase = new ListVideoUseCase(videoGateway);
        assertNotNull(useCase.listVideo("1"));
    }

    @Test
    void listVideoThrowsExceptionWhenVideoNotFound() {
        when(videoGateway.findById(anyString())).thenReturn(Optional.empty());
        ListVideoUseCase useCase = new ListVideoUseCase(videoGateway);
        assertThrows(IllegalArgumentException.class, () -> useCase.listVideo("1"));
    }
}
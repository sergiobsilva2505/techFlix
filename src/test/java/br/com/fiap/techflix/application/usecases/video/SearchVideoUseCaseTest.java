package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.application.ports.VideoSearchPort;
import br.com.fiap.techflix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SearchVideoUseCaseTest {

    private VideoGateway videoGateway;
    private SearchVideoUseCase searchVideoUseCase;

    @BeforeEach
    void setUp() {
        videoGateway = mock(VideoGateway.class);
        searchVideoUseCase = new SearchVideoUseCase(videoGateway);
    }

    @Test
    void shouldReturnPagePortWhenVideosMatchSearch() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        PagePort<Video> pagePort = mock(PagePort.class);
        when(videoGateway.searchVideos(videoSearchPort)).thenReturn(pagePort);

        PagePort<Video> result = searchVideoUseCase.searchVideos(videoSearchPort);

        assertEquals(pagePort, result);
        verify(videoGateway).searchVideos(videoSearchPort);
    }

    @Test
    void shouldReturnEmptyPagePortWhenNoVideosMatchSearch() {
        VideoSearchPort videoSearchPort = mock(VideoSearchPort.class);
        PagePort<Video> pagePort = mock(PagePort.class);
        when(pagePort.getContent()).thenReturn(Collections.emptyList());
        when(videoGateway.searchVideos(videoSearchPort)).thenReturn(pagePort);

        PagePort<Video> result = searchVideoUseCase.searchVideos(videoSearchPort);

        assertTrue(result.getContent().isEmpty());
        verify(videoGateway).searchVideos(videoSearchPort);
    }
}
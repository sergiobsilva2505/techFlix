package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.VideoPublishPort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PublishVideoUseCaseTest {

    private CategoryGateway categoryGateway;
    private VideoGateway videoGateway;
    private PublishVideoUseCase publishVideoUseCase;

    @BeforeEach
    void setUp() {
        categoryGateway = mock(CategoryGateway.class);
        videoGateway = mock(VideoGateway.class);
        publishVideoUseCase = new PublishVideoUseCase(categoryGateway, videoGateway);
    }

    @Test
    void shouldPublishVideoSuccessfully() {
        VideoPublishPort videoPublishPort = mock(VideoPublishPort.class);
        Video video = mock(Video.class);
        Category category = mock(Category.class);
        when(videoPublishPort.categoryNames()).thenReturn(List.of("Action"));
        when(categoryGateway.findAllByNameIn(anyList())).thenReturn(List.of(category));
        when(videoGateway.save(any(), anyList(), any(LocalDateTime.class))).thenReturn(video);

        Video result = publishVideoUseCase.publishVideo(videoPublishPort);

        assertEquals(video, result);
        verify(videoGateway).save(any(), anyList(), any(LocalDateTime.class));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        VideoPublishPort videoPublishPort = mock(VideoPublishPort.class);
        when(videoPublishPort.categoryNames()).thenReturn(Arrays.asList("Action", "Adventure"));
        when(categoryGateway.findAllByNameIn(anyList())).thenReturn(Collections.singletonList(mock(Category.class)));

        assertThrows(IllegalArgumentException.class, () -> publishVideoUseCase.publishVideo(videoPublishPort));
    }
}
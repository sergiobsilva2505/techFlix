package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.VideoUpdatePort;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateVideoUseCaseTest {

    private CategoryGateway categoryGateway;
    private VideoGateway videoGateway;
    private UpdateVideoUseCase updateVideoUseCase;

    @BeforeEach
    void setUp() {
        categoryGateway = mock(CategoryGateway.class);
        videoGateway = mock(VideoGateway.class);
        updateVideoUseCase = new UpdateVideoUseCase(categoryGateway, videoGateway);
    }

    @Test
    void shouldUpdateVideoWhenUpdateVideoIsCalledWithValidIdAndCategories() {
        String videoId = "videoId";
        VideoUpdatePort videoUpdatePort = mock(VideoUpdatePort.class);
        Video video = mock(Video.class);
        Category category = mock(Category.class);

        when(videoGateway.findById(videoId)).thenReturn(Optional.of(video));
        when(categoryGateway.findAllByNameIn(anyList())).thenReturn(Collections.singletonList(category));
        when(videoUpdatePort.categoryNames()).thenReturn(List.of("Category 1"));
        when(videoGateway.update(video, videoUpdatePort, Collections.singletonList(category))).thenReturn(video);

        Video updatedVideo = updateVideoUseCase.updateVideo(videoId, videoUpdatePort);

        assertEquals(video, updatedVideo);
        verify(videoGateway, times(1)).update(video, videoUpdatePort, Collections.singletonList(category));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenUpdateVideoIsCalledWithNonExistingId() {
        String videoId = "nonExistingId";
        VideoUpdatePort videoUpdatePort = mock(VideoUpdatePort.class);

        when(videoGateway.findById(videoId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> updateVideoUseCase.updateVideo(videoId, videoUpdatePort));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenUpdateVideoIsCalledWithNonExistingCategories() {
        String videoId = "videoId";
        VideoUpdatePort videoUpdatePort = mock(VideoUpdatePort.class);
        Video video = mock(Video.class);

        when(videoGateway.findById(videoId)).thenReturn(Optional.of(video));
        when(categoryGateway.findAllByNameIn(anyList())).thenReturn(List.of());
        when(videoUpdatePort.categoryNames()).thenReturn(List.of("NonExistingCategory"));

        assertThrows(IllegalArgumentException.class, () -> updateVideoUseCase.updateVideo(videoId, videoUpdatePort));
    }
}
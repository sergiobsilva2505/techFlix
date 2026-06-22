package br.com.fiap.techflix.application.usecases.video;

import br.com.fiap.techflix.application.gateways.category.CategoryGateway;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.application.ports.VideoUpdatePort;
import br.com.fiap.techflix.domain.entities.category.Category;
import br.com.fiap.techflix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Atualiza vídeo com ID e categorias válidos")
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
    @DisplayName("Lança exceção quando ID do vídeo não existe")
    void shouldThrowIllegalArgumentExceptionWhenUpdateVideoIsCalledWithNonExistingId() {
        String videoId = "nonExistingId";
        VideoUpdatePort videoUpdatePort = mock(VideoUpdatePort.class);

        when(videoGateway.findById(videoId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> updateVideoUseCase.updateVideo(videoId, videoUpdatePort));
    }

    @Test
    @DisplayName("Lança exceção quando categoria informada não existe")
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
package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.UserBookmarkedCategoriesPort;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoRecommendationsUseCaseTest {

    private VideoGateway videoGateway;
    private VideoRecommendationsUseCase videoRecommendationsUseCase;

    @BeforeEach
    void setUp() {
        videoGateway = mock(VideoGateway.class);
        videoRecommendationsUseCase = new VideoRecommendationsUseCase(videoGateway);
    }

    @Test
    void shouldReturnUserRecommendationsWhenUserHasLikedCategories() {
        String userId = "existingUserId";
        UserBookmarkedCategoriesPort category = mock(UserBookmarkedCategoriesPort.class);
        Video video = mock(Video.class);
        when(videoGateway.getLikedCategories(userId)).thenReturn(Collections.singletonList(category));
        when(videoGateway.getRecommendations(Collections.singletonList(category))).thenReturn(Collections.singletonList(video));

        List<Video> result = videoRecommendationsUseCase.getRecommendations(userId);

        assertFalse(result.isEmpty());
        assertEquals(video, result.getFirst());
        verify(videoGateway).getLikedCategories(userId);
        verify(videoGateway, never()).getRecommendations();
        verify(videoGateway).getRecommendations(Collections.singletonList(category));
    }

    @Test
    void shouldReturnGeneralRecommendationsWhenUserHasNoLikedCategories() {
        String userId = "existingUserId";
        Video video = mock(Video.class);
        when(videoGateway.getLikedCategories(userId)).thenReturn(Collections.emptyList());
        when(videoGateway.getRecommendations()).thenReturn(Collections.singletonList(video));

        List<Video> result = videoRecommendationsUseCase.getRecommendations(userId);

        assertFalse(result.isEmpty());
        assertEquals(video, result.getFirst());
        verify(videoGateway).getLikedCategories(userId);
        verify(videoGateway).getRecommendations();
        verify(videoGateway, never()).getRecommendations(any());
    }
}
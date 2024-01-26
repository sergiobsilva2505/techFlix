package br.com.fiap.techFlix.application.useCases.bookmark;

import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateBookmarkVideoUseCaseTest {

    @Mock
    private BookmarkVideoGateway bookmarkVideoGateway;
    @Mock
    private UserGateway userGateway;
    @Mock
    private VideoGateway videoGateway;

    AutoCloseable openMocks;

    private CreateBookmarkVideoUseCase createBookmarkVideoUseCase;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        createBookmarkVideoUseCase = new CreateBookmarkVideoUseCase(bookmarkVideoGateway, userGateway, videoGateway);
    }

    @Test
    void shouldCreateBookmarkVideoWhenUserAndVideoExistAndNotBookmarked() {
        User user = mock(User.class);
        Video video = mock(Video.class);
        when(userGateway.findById("2")).thenReturn(Optional.of(user));
        when(videoGateway.findById("2")).thenReturn(Optional.of(video));
        when(bookmarkVideoGateway.existsByVideoIdAndUserId("2", "2")).thenReturn(false);
        when(bookmarkVideoGateway.create(user, video)).thenReturn(mock(BookmarkVideo.class));

        BookmarkVideo bookmarkVideo = createBookmarkVideoUseCase.createBookmarkVideo("2", "2");

        assertNotNull(bookmarkVideo);
        verify(videoGateway).likeVideo("2");
        verify(bookmarkVideoGateway).create(user, video);
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        when(userGateway.findById("2")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> createBookmarkVideoUseCase.createBookmarkVideo("2", "2"));

        verify(videoGateway, never()).likeVideo(any());
        verify(bookmarkVideoGateway, never()).create(any(), any());
    }

    @Test
    void shouldThrowExceptionWhenVideoDoesNotExist() {
        User user = mock(User.class);
        when(userGateway.findById("2")).thenReturn(Optional.of(user));
        when(videoGateway.findById("2")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> createBookmarkVideoUseCase.createBookmarkVideo("2", "2"));

        verify(videoGateway, never()).likeVideo(any());
        verify(bookmarkVideoGateway, never()).create(any(), any());
    }

    @Test
    void shouldThrowExceptionWhenVideoIsAlreadyBookmarked() {
        User user = mock(User.class);
        Video video = mock(Video.class);
        when(userGateway.findById("2")).thenReturn(Optional.of(user));
        when(videoGateway.findById("2")).thenReturn(Optional.of(video));
        when(bookmarkVideoGateway.existsByVideoIdAndUserId("2", "2")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> createBookmarkVideoUseCase.createBookmarkVideo("2", "2"));

        verify(videoGateway, never()).likeVideo(any());
        verify(bookmarkVideoGateway, never()).create(any(), any());
    }
}
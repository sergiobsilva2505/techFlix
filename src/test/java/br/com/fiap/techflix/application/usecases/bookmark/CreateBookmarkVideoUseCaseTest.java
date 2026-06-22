package br.com.fiap.techflix.application.usecases.bookmark;

import br.com.fiap.techflix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techflix.application.gateways.user.UserGateway;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;
import br.com.fiap.techflix.domain.entities.user.User;
import br.com.fiap.techflix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateBookmarkVideoUseCaseTest {

    private BookmarkVideoGateway bookmarkVideoGateway;
    private UserGateway userGateway;
    private VideoGateway videoGateway;
    private CreateBookmarkVideoUseCase createBookmarkVideoUseCase;

    @BeforeEach
    void setUp() {
        bookmarkVideoGateway = mock(BookmarkVideoGateway.class);
        userGateway = mock(UserGateway.class);
        videoGateway = mock(VideoGateway.class);
        createBookmarkVideoUseCase = new CreateBookmarkVideoUseCase(bookmarkVideoGateway, userGateway, videoGateway);
    }

    @Test
    @DisplayName("Cria bookmark quando usuário e vídeo existem e ainda não foram marcados")
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
    @DisplayName("Lança exceção quando usuário não existe")
    void shouldThrowExceptionWhenUserDoesNotExist() {
        when(userGateway.findById("2")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> createBookmarkVideoUseCase.createBookmarkVideo("2", "2"));

        verify(videoGateway, never()).likeVideo(any());
        verify(bookmarkVideoGateway, never()).create(any(), any());
    }

    @Test
    @DisplayName("Lança exceção quando vídeo não existe")
    void shouldThrowExceptionWhenVideoDoesNotExist() {
        User user = mock(User.class);
        when(userGateway.findById("2")).thenReturn(Optional.of(user));
        when(videoGateway.findById("2")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> createBookmarkVideoUseCase.createBookmarkVideo("2", "2"));

        verify(videoGateway, never()).likeVideo(any());
        verify(bookmarkVideoGateway, never()).create(any(), any());
    }

    @Test
    @DisplayName("Lança exceção quando vídeo já foi marcado como favorito")
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
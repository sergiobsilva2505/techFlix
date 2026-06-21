package br.com.fiap.techflix.application.usecases.bookmark;

import br.com.fiap.techflix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteBookmarkVideoUseCaseTest {

    private BookmarkVideoGateway bookmarkVideoGateway;
    private VideoGateway videoGateway;
    private DeleteBookmarkVideoUseCase deleteBookmarkVideoUseCase;

    @BeforeEach
    void setUp() {
        bookmarkVideoGateway = mock(BookmarkVideoGateway.class);
        videoGateway = mock(VideoGateway.class);
        deleteBookmarkVideoUseCase = new DeleteBookmarkVideoUseCase(bookmarkVideoGateway, videoGateway);
    }

    @Test
    void shouldDeleteBookmarkVideoWhenBookmarkExists() {
        BookmarkVideo bookmarkVideo = mock(BookmarkVideo.class);
        when(bookmarkVideo.getId()).thenReturn("1");
        when(bookmarkVideoGateway.findByVideoIdAndUserId("2", "2")).thenReturn(Optional.of(bookmarkVideo));

        deleteBookmarkVideoUseCase.deleteBookmarkVideo("2", "2");

        verify(videoGateway).unlikeVideo("2");
        verify(bookmarkVideoGateway).deleteById("1");
    }

    @Test
    void shouldThrowExceptionWhenBookmarkDoesNotExist() {
        when(bookmarkVideoGateway.findByVideoIdAndUserId("2", "2")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> deleteBookmarkVideoUseCase.deleteBookmarkVideo("2", "2"));

        verify(videoGateway, never()).unlikeVideo(any());
        verify(bookmarkVideoGateway, never()).deleteById(any());
    }

}
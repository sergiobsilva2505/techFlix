package br.com.fiap.techFlix.application.useCases.bookmark;

import br.com.fiap.techFlix.adapter.web.bookmarkvideo.BookmarkVideoShowDTO;
import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListBookmarkVideoUseCaseTest {

    private BookmarkVideoGateway bookmarkVideoGateway;
    private ListBookmarkVideoUseCase listBookmarkVideoUseCase;

    @BeforeEach
    void setUp() {
        bookmarkVideoGateway = mock(BookmarkVideoGateway.class);
        listBookmarkVideoUseCase = new ListBookmarkVideoUseCase(bookmarkVideoGateway);
    }

    @Test
    void shouldListBookmarkVideoWhenBookmarkExistsForUser() {
        BookmarkVideo bookmarkVideo = mock(BookmarkVideo.class);
        when(bookmarkVideoGateway.findByUserId("2")).thenReturn(Optional.of(bookmarkVideo));

        BookmarkVideo result = listBookmarkVideoUseCase.listBookmarkVideo("2");

        assertNotNull(result);
        assertEquals(bookmarkVideo, result);
    }

    @Test
    void shouldThrowExceptionWhenBookmarkDoesNotExistForUser() {
        when(bookmarkVideoGateway.findByUserId("2")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> listBookmarkVideoUseCase.listBookmarkVideo("2"));
    }

    @Test
    void shouldListAllBookmarkVideosWithGivenPageAndSize() {
        PagePort pagePort = mock(PagePort.class);
        PagePort pagePortMapped = mock(PagePort.class);
        when(pagePortMapped.getTotalElements()).thenReturn(1L);
        when(pagePort.map(any())).thenReturn(pagePortMapped);
        when(bookmarkVideoGateway.allBookmarkVideo(0, 10)).thenReturn(pagePort);

        PagePort<BookmarkVideoShowDTO> result = listBookmarkVideoUseCase.listAllBookmarkVideo(0, 10);

        assertNotNull(result);
        assertEquals(1L, result.getTotalElements());
    }

}
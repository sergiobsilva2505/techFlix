package br.com.fiap.techFlix.adapter.web.bookmarkvideo;

import br.com.fiap.techFlix.adapter.web.PageDTO;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.application.useCases.bookmark.*;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VideoBookmarkControllerTest {

    @Mock
    ListBookmarkVideoUseCase listBookmarkVideoUseCase;

    @Mock
    CreateBookmarkVideoUseCase createBookmarkVideoUseCase;

    @Mock
    DeleteBookmarkVideoUseCase deleteBookmarkVideoUseCase;

    @InjectMocks
    VideoBookmarkController videoBookmarkController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateBookmarkVideo() {
        BookmarkVideo bookmarkVideo = mock(BookmarkVideo.class);
        when(bookmarkVideo.getId()).thenReturn("id");
        when(createBookmarkVideoUseCase.createBookmarkVideo(anyString(), anyString())).thenReturn(bookmarkVideo);

        ResponseEntity<String> response = videoBookmarkController.createBookmarkVideo("videoId", "userId");

        assertEquals(201, response.getStatusCodeValue());
        verify(createBookmarkVideoUseCase).createBookmarkVideo(anyString(), anyString());
    }

    @Test
    void shouldDeleteBookmarkVideo() {
        doNothing().when(deleteBookmarkVideoUseCase).deleteBookmarkVideo(anyString(), anyString());

        ResponseEntity<Void> response = videoBookmarkController.deleteBookmarkVideo("videoId", "userId");

        assertEquals(204, response.getStatusCodeValue());
        verify(deleteBookmarkVideoUseCase).deleteBookmarkVideo(anyString(), anyString());
    }

    @Test
    void shouldGetBookmarkVideoById() {
        BookmarkVideo bookmarkVideo = mock(BookmarkVideo.class);
        User user = mock(User.class);
        Video video = mock(Video.class);
        when(bookmarkVideo.getUser()).thenReturn(user);
        when(bookmarkVideo.getVideo()).thenReturn(video);
        when(listBookmarkVideoUseCase.listBookmarkVideo(anyString())).thenReturn(bookmarkVideo);

        ResponseEntity<BookmarkVideoShowDTO> response = videoBookmarkController.getBookmarkVideoById("id");

        assertEquals(200, response.getStatusCodeValue());
        verify(listBookmarkVideoUseCase).listBookmarkVideo(anyString());
    }

    @Test
    void shouldGetAllBookmarkVideo() {
        PagePort<BookmarkVideoShowDTO> pagePort = new PageDTO<>(Page.empty());
        when(listBookmarkVideoUseCase.listAllBookmarkVideo(anyInt(), anyInt())).thenReturn(pagePort);

        ResponseEntity<PagePort<BookmarkVideoShowDTO>> response = videoBookmarkController.getAllBookmarkVideo(0, 10);

        assertEquals(200, response.getStatusCodeValue());
        verify(listBookmarkVideoUseCase).listAllBookmarkVideo(anyInt(), anyInt());
    }
}
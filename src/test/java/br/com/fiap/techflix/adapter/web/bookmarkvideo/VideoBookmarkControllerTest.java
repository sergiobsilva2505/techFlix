package br.com.fiap.techflix.adapter.web.bookmarkvideo;

import br.com.fiap.techflix.adapter.web.PageDTO;
import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.application.usecases.bookmark.*;
import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;
import br.com.fiap.techflix.domain.entities.user.User;
import br.com.fiap.techflix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Cria bookmark de vídeo com sucesso")
    void shouldCreateBookmarkVideo() {
        BookmarkVideo bookmarkVideo = mock(BookmarkVideo.class);
        when(bookmarkVideo.getId()).thenReturn("id");
        when(createBookmarkVideoUseCase.createBookmarkVideo(anyString(), anyString())).thenReturn(bookmarkVideo);

        ResponseEntity<String> response = videoBookmarkController.createBookmarkVideo("videoId", "userId");

        assertEquals(201, response.getStatusCode().value());
        verify(createBookmarkVideoUseCase).createBookmarkVideo(anyString(), anyString());
    }

    @Test
    @DisplayName("Remove bookmark de vídeo com sucesso")
    void shouldDeleteBookmarkVideo() {
        doNothing().when(deleteBookmarkVideoUseCase).deleteBookmarkVideo(anyString(), anyString());

        ResponseEntity<Void> response = videoBookmarkController.deleteBookmarkVideo("videoId", "userId");

        assertEquals(204, response.getStatusCode().value());
        verify(deleteBookmarkVideoUseCase).deleteBookmarkVideo(anyString(), anyString());
    }

    @Test
    @DisplayName("Retorna bookmark de vídeo pelo ID")
    void shouldGetBookmarkVideoById() {
        BookmarkVideo bookmarkVideo = mock(BookmarkVideo.class);
        User user = mock(User.class);
        Video video = mock(Video.class);
        when(bookmarkVideo.getUser()).thenReturn(user);
        when(bookmarkVideo.getVideo()).thenReturn(video);
        when(listBookmarkVideoUseCase.listBookmarkVideo(anyString())).thenReturn(bookmarkVideo);

        ResponseEntity<BookmarkVideoShowDTO> response = videoBookmarkController.getBookmarkVideoById("id");

        assertEquals(200, response.getStatusCode().value());
        verify(listBookmarkVideoUseCase).listBookmarkVideo(anyString());
    }

    @Test
    @DisplayName("Retorna todos os bookmarks de vídeo paginados")
    void shouldGetAllBookmarkVideo() {
        PagePort<BookmarkVideoShowDTO> pagePort = new PageDTO<>(Page.empty());
        when(listBookmarkVideoUseCase.listAllBookmarkVideo(anyInt(), anyInt())).thenReturn(pagePort);

        ResponseEntity<PagePort<BookmarkVideoShowDTO>> response = videoBookmarkController.getAllBookmarkVideo(0, 10);

        assertEquals(200, response.getStatusCode().value());
        verify(listBookmarkVideoUseCase).listAllBookmarkVideo(anyInt(), anyInt());
    }
}
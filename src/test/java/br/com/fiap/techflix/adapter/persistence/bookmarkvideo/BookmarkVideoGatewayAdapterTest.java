package br.com.fiap.techflix.adapter.persistence.bookmarkvideo;

import br.com.fiap.techflix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techflix.adapter.persistence.user.UserDocument;
import br.com.fiap.techflix.adapter.persistence.video.VideoDetailsDocument;
import br.com.fiap.techflix.adapter.persistence.video.VideoDocument;
import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;
import br.com.fiap.techflix.domain.entities.user.User;
import br.com.fiap.techflix.domain.entities.video.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BookmarkVideoGatewayAdapterTest {

    private BookmarkVideoRepository bookmarkVideoRepository;
    private BookmarkVideoGatewayAdapter bookmarkVideoGatewayAdapter;

    @BeforeEach
    void setup() {
        bookmarkVideoRepository = mock(BookmarkVideoRepository.class);
        bookmarkVideoGatewayAdapter = new BookmarkVideoGatewayAdapter(bookmarkVideoRepository);
    }

    @Test
    @DisplayName("Cria bookmark de vídeo com sucesso")
    void createBookmarkVideoSuccessfully() {
        User user = mock(User.class);
        Video video = mock(Video.class);
        BookmarkVideoDocument document = createBookmarkVideoDocument();
        when(bookmarkVideoRepository.save(any())).thenReturn(document);
        BookmarkVideo result = bookmarkVideoGatewayAdapter.create(user, video);
        assertNotNull(result);
        verify(bookmarkVideoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Retorna bookmark quando ID existe")
    void findByIdReturnsBookmarkVideoWhenExists() {
        String bookmarkId = "123";
        BookmarkVideoDocument document = createBookmarkVideoDocument();
        when(bookmarkVideoRepository.findById(bookmarkId)).thenReturn(Optional.of(document));
        Optional<BookmarkVideo> result = bookmarkVideoGatewayAdapter.findById(bookmarkId);
        assertTrue(result.isPresent());
        verify(bookmarkVideoRepository, times(1)).findById(bookmarkId);
    }

    @Test
    @DisplayName("Retorna vazio quando ID não existe")
    void findByIdReturnsEmptyWhenDoesNotExist() {
        String bookmarkId = "123";
        when(bookmarkVideoRepository.findById(bookmarkId)).thenReturn(Optional.empty());
        Optional<BookmarkVideo> result = bookmarkVideoGatewayAdapter.findById(bookmarkId);
        assertTrue(result.isEmpty());
        verify(bookmarkVideoRepository).findById(bookmarkId);
    }

    @Test
    @DisplayName("Retorna página de bookmarks")
    void allBookmarkVideoReturnsPage() {
        int page = 0;
        int size = 10;
        when(bookmarkVideoRepository.findAll(any(PageRequest.class))).thenReturn(Page.empty());
        PagePort<BookmarkVideo> result = bookmarkVideoGatewayAdapter.allBookmarkVideo(page, size);
        assertNotNull(result);
        verify(bookmarkVideoRepository).findAll(any(PageRequest.class));
    }

    @Test
    @DisplayName("Retorna bookmark pelo ID do vídeo e do usuário")
    void findByVideoIdAndUserIdReturnsBookmarkVideo() {
        String videoId = "123";
        String userId = "456";
        BookmarkVideoDocument document = createBookmarkVideoDocument();
        when(bookmarkVideoRepository.findByVideoIdAndUserId(videoId, userId)).thenReturn(Optional.of(document));
        Optional<BookmarkVideo> result = bookmarkVideoGatewayAdapter.findByVideoIdAndUserId(videoId, userId);
        assertTrue(result.isPresent());
        verify(bookmarkVideoRepository, times(1)).findByVideoIdAndUserId(videoId, userId);
    }

    @Test
    @DisplayName("Retorna verdadeiro quando existe bookmark para o vídeo e usuário")
    void existsByVideoIdAndUserIdReturnsTrue() {
        String videoId = "123";
        String userId = "456";
        when(bookmarkVideoRepository.existsByUser_IdAndVideo_Id(userId, videoId)).thenReturn(true);
        boolean result = bookmarkVideoGatewayAdapter.existsByVideoIdAndUserId(videoId, userId);
        assertTrue(result);
        verify(bookmarkVideoRepository).existsByUser_IdAndVideo_Id(userId, videoId);
    }

    @Test
    @DisplayName("Deleta bookmark por ID com sucesso")
    void deleteByIdExecutesSuccessfully() {
        String id = "123";
        doNothing().when(bookmarkVideoRepository).deleteById(id);
        bookmarkVideoGatewayAdapter.deleteById(id);
        verify(bookmarkVideoRepository, times(1)).deleteById(id);
    }

    private UserDocument getUserDocument() {
        return new UserDocument("123", "name", "email", "password", "token");
    }

    private VideoDocument getVideoDocument() {
        return new VideoDocument("123", "title", "description", List.of(new CategoryDocument("123", "action")), new VideoDetailsDocument(0, 0), LocalDateTime.now());
    }

    private BookmarkVideoDocument createBookmarkVideoDocument() {
        return new BookmarkVideoDocument("123", getUserDocument(), getVideoDocument());
    }
}
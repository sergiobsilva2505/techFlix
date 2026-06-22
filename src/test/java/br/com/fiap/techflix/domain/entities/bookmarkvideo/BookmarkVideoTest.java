package br.com.fiap.techflix.domain.entities.bookmarkvideo;

import br.com.fiap.techflix.domain.entities.user.User;
import br.com.fiap.techflix.domain.entities.video.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class BookmarkVideoTest {

    @Test
    @DisplayName("Cria bookmark de vídeo com parâmetros válidos")
    void shouldCreateBookmarkVideoWithValidParameters() {
        User user = mock(User.class);
        Video video = mock(Video.class);
        BookmarkVideo bookmarkVideo = new BookmarkVideo("1", user, video);

        assertEquals("1", bookmarkVideo.getId());
        assertEquals(user, bookmarkVideo.getUser());
        assertEquals(video, bookmarkVideo.getVideo());
    }

    @Test
    @DisplayName("Lança exceção quando ID é nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        User user = mock(User.class);
        Video video = mock(Video.class);

        assertThrows(IllegalArgumentException.class, () -> new BookmarkVideo(null, user, video));
    }

    @Test
    @DisplayName("Lança exceção quando usuário é nulo")
    void shouldThrowExceptionWhenUserIsNull() {
        Video video = mock(Video.class);

        assertThrows(IllegalArgumentException.class, () -> new BookmarkVideo("1", null, video));
    }

    @Test
    @DisplayName("Lança exceção quando vídeo é nulo")
    void shouldThrowExceptionWhenVideoIsNull() {
        User user = mock(User.class);

        assertThrows(IllegalArgumentException.class, () -> new BookmarkVideo("1", user, null));
    }
}
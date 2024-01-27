package br.com.fiap.techFlix.domain.entities.bookmarvideo;

import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class BookmarkVideoTest {

    @Test
    void shouldCreateBookmarkVideoWithValidParameters() {
        User user = mock(User.class);
        Video video = mock(Video.class);
        BookmarkVideo bookmarkVideo = new BookmarkVideo("1", user, video);

        assertEquals("1", bookmarkVideo.getId());
        assertEquals(user, bookmarkVideo.getUser());
        assertEquals(video, bookmarkVideo.getVideo());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        User user = mock(User.class);
        Video video = mock(Video.class);

        assertThrows(IllegalArgumentException.class, () -> new BookmarkVideo(null, user, video));
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        Video video = mock(Video.class);

        assertThrows(IllegalArgumentException.class, () -> new BookmarkVideo("1", null, video));
    }

    @Test
    void shouldThrowExceptionWhenVideoIsNull() {
        User user = mock(User.class);

        assertThrows(IllegalArgumentException.class, () -> new BookmarkVideo("1", user, null));
    }
}
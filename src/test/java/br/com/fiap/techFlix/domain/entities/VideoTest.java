package br.com.fiap.techFlix.domain.entities;

import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.domain.entities.video.Video;
import br.com.fiap.techFlix.domain.entities.video.VideoDetails;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    @Test
    void shouldCreateVideoWithValidParameters() {
        VideoDetails details = new VideoDetails(100, 10);
        Category category = new Category("1", "Action");
        Video video = new Video("1", "Title", "Description", List.of(category), details, LocalDateTime.now());

        assertEquals("1", video.getId());
        assertEquals("Title", video.getTitle());
        assertEquals("Description", video.getDescription());
        assertEquals(List.of(category), video.getCategories());
        assertEquals(details, video.getDetails());
        assertNotNull(video.getPublicationDate());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        VideoDetails details = new VideoDetails(100, 10);
        Category category = new Category("1", "Action");

        assertThrows(IllegalArgumentException.class, () -> new Video(null, "Title", "Description", List.of(category), details, LocalDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenTitleIsNull() {
        VideoDetails details = new VideoDetails(100, 10);
        Category category = new Category("1", "Action");

        assertThrows(IllegalArgumentException.class, () -> new Video("1", null, "Description", List.of(category), details, LocalDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        VideoDetails details = new VideoDetails(100, 10);
        Category category = new Category("1", "Action");

        assertThrows(IllegalArgumentException.class, () -> new Video("1", "Title", null, List.of(category), details, LocalDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenCategoriesIsNull() {
        VideoDetails details = new VideoDetails(100, 10);

        assertThrows(IllegalArgumentException.class, () -> new Video("1", "Title", "Description", null, details, LocalDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenDetailsIsNull() {
        Category category = new Category("1", "Action");

        assertThrows(IllegalArgumentException.class, () -> new Video("1", "Title", "Description", List.of(category), null, LocalDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenPublicationDateIsNull() {
        VideoDetails details = new VideoDetails(100, 10);
        Category category = new Category("1", "Action");

        assertThrows(IllegalArgumentException.class, () -> new Video("1", "Title", "Description", List.of(category), details, null));
    }
}
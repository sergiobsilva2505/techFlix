package br.com.fiap.techFlix.domain.entities;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    @Mock
    private Category category;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void createVideoWithValidParameters() {
        Video video = new Video("title", "description", category, LocalDateTime.now());
        assertNotNull(video);
        assertEquals("title", video.getTitle());
        assertEquals("description", video.getDescription());
        assertEquals(category, video.getCategory());
        assertNotNull(video.getPublicationDate());
    }

    @Test
    void createVideoWithEmptyTitleShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Video("", "description", category, LocalDateTime.now());
        });
    }

    @Test
    void createVideoWithEmptyDescriptionShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Video("title", "", category, LocalDateTime.now());
        });
    }

    @Test
    void createVideoWithNullCategoryShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Video("title", "description", null, LocalDateTime.now());
        });
    }

    @Test
    void createVideoWithNullPublicationDateShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Video("title", "description", category, null);
        });
    }

    @Test
    void getUrlShouldReturnCorrectUrl() {
        Video video = new Video("1", "title", "description", category, LocalDateTime.now());
        assertEquals("/videos/play/1", video.getUrl().toString());
    }

    @Test
    void getUrlWithEmptyIdShouldThrowException() {
        Video video = new Video("title", "description", category, LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, video::getUrl);
    }
}
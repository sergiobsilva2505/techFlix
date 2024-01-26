package br.com.fiap.techFlix.domain.entities.video;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoDetailsTest {

    @Test
    void shouldCreateVideoDetailsWithValidParameters() {
        VideoDetails details = new VideoDetails(100, 10);

        assertEquals(100, details.getLikes());
        assertEquals(10, details.getViews());
    }

    @Test
    void shouldThrowExceptionWhenLikesIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new VideoDetails(-1, 10));
    }

    @Test
    void shouldThrowExceptionWhenViewsIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new VideoDetails(100, -1));
    }
}
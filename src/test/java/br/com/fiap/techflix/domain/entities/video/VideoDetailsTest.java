package br.com.fiap.techflix.domain.entities.video;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VideoDetailsTest {
    @Test
    @DisplayName("Cria detalhes do vídeo com parâmetros válidos")
    void shouldCreateVideoDetailsWithValidParameters() {
        VideoDetails details = new VideoDetails(100, 10);

        assertEquals(100, details.getLikes());
        assertEquals(10, details.getViews());
    }

    @Test
    @DisplayName("Lança exceção quando curtidas são negativas")
    void shouldThrowExceptionWhenLikesIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new VideoDetails(-1, 10));
    }

    @Test
    @DisplayName("Lança exceção quando visualizações são negativas")
    void shouldThrowExceptionWhenViewsIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new VideoDetails(100, -1));
    }
}
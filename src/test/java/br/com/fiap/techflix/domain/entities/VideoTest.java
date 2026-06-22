package br.com.fiap.techflix.domain.entities;

import br.com.fiap.techflix.domain.entities.category.Category;
import br.com.fiap.techflix.domain.entities.video.Video;
import br.com.fiap.techflix.domain.entities.video.VideoDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    @Test
    @DisplayName("Cria vídeo com parâmetros válidos")
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

    @ParameterizedTest
    @MethodSource("provideNullTextFields")
    @DisplayName("Lança exceção quando campo de texto obrigatório é nulo")
    void shouldThrowExceptionWhenRequiredTextFieldIsNull(String id, String title, String description) {
        VideoDetails details = new VideoDetails(100, 10);
        List<Category> categories = List.of(new Category("1", "Action"));
        LocalDateTime now = LocalDateTime.now();

        assertThrows(IllegalArgumentException.class, () -> new Video(id, title, description, categories, details, now));
    }

    static Stream<Object[]> provideNullTextFields() {
        return Stream.of(
            new Object[]{null, "Title", "Description"},
            new Object[]{"1", null, "Description"},
            new Object[]{"1", "Title", null}
        );
    }

    @Test
    @DisplayName("Lança exceção quando categorias são nulas")
    void shouldThrowExceptionWhenCategoriesIsNull() {
        VideoDetails details = new VideoDetails(100, 10);
        LocalDateTime now = LocalDateTime.now();

        assertThrows(IllegalArgumentException.class, () -> new Video("1", "Title", "Description", null, details, now));
    }

    @Test
    @DisplayName("Lança exceção quando detalhes são nulos")
    void shouldThrowExceptionWhenDetailsIsNull() {
        Category category = new Category("1", "Action");
        List<Category> categories = List.of(category);
        LocalDateTime now = LocalDateTime.now();

        assertThrows(IllegalArgumentException.class, () -> new Video("1", "Title", "Description", categories, null, now));
    }

    @Test
    @DisplayName("Lança exceção quando data de publicação é nula")
    void shouldThrowExceptionWhenPublicationDateIsNull() {
        VideoDetails details = new VideoDetails(100, 10);
        Category category = new Category("1", "Action");
        List<Category> categories = List.of(category);

        assertThrows(IllegalArgumentException.class, () -> new Video("1", "Title", "Description", categories, details, null));
    }
}
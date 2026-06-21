package br.com.fiap.techflix.domain.entities.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileTest {
    @Test
    @DisplayName("Cria arquivo com parâmetros válidos")
    void shouldCreateFileWithValidParameters() {
        byte[] content = new byte[10];
        File file = new File("1", "file1", "video/mp4", 10, content);

        assertEquals("1", file.getId());
        assertEquals("file1", file.getName());
        assertEquals("video/mp4", file.getContentType());
        assertEquals(10, file.getSize());
    }

    @Test
    @DisplayName("Lança exceção quando ID é nulo")
    void shouldThrowExceptionWhenIdIsNull() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File(null, "file1", "video/mp4", 10, content));
    }

    @Test
    @DisplayName("Lança exceção quando nome é nulo")
    void shouldThrowExceptionWhenNameIsNull() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File("1", null, "video/mp4", 10, content));
    }

    @Test
    @DisplayName("Lança exceção quando tipo de conteúdo é nulo")
    void shouldThrowExceptionWhenContentTypeIsNull() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File("1", "file1", null, 10, content));
    }

    @Test
    @DisplayName("Lança exceção quando tamanho é zero")
    void shouldThrowExceptionWhenSizeIsZero() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File("1", "file1", "video/mp4", 0, content));
    }

    @Test
    @DisplayName("Lança exceção quando conteúdo é nulo")
    void shouldThrowExceptionWhenContentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new File("1", "file1", "video/mp4", 10, null));
    }
}
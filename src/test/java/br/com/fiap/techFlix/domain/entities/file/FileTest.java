package br.com.fiap.techFlix.domain.entities.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileTest {

    @Test
    void shouldCreateFileWithValidParameters() {
        byte[] content = new byte[10];
        File file = new File("1", "file1", "video/mp4", 10, content);

        assertEquals("1", file.getId());
        assertEquals("file1", file.getName());
        assertEquals("video/mp4", file.getContentType());
        assertEquals(10, file.getSize());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File(null, "file1", "video/mp4", 10, content));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File("1", null, "video/mp4", 10, content));
    }

    @Test
    void shouldThrowExceptionWhenContentTypeIsNull() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File("1", "file1", null, 10, content));
    }

    @Test
    void shouldThrowExceptionWhenSizeIsZero() {
        byte[] content = new byte[10];

        assertThrows(IllegalArgumentException.class, () -> new File("1", "file1", "video/mp4", 0, content));
    }

    @Test
    void shouldThrowExceptionWhenContentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new File("1", "file1", "video/mp4", 10, null));
    }
}
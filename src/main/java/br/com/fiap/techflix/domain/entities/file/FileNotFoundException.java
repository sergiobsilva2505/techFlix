package br.com.fiap.techflix.domain.entities.file;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String id) {
        super("File not found with id: " + id);
    }
}


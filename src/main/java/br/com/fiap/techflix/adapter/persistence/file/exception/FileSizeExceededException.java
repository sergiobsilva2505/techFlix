package br.com.fiap.techflix.adapter.persistence.file.exception;

public class FileSizeExceededException extends RuntimeException {
    public FileSizeExceededException(String fileSizeExceedsMaximumLimit) {
        super(fileSizeExceedsMaximumLimit);
    }
}

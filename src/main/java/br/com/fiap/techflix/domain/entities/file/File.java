package br.com.fiap.techflix.domain.entities.file;

import br.com.fiap.techflix.domain.validation.Validator;

public class File {

    private final String id;
    private final String name;
    private final String contentType;
    private final long size;
    private final byte[] content;

    public File(String id, String name, String contentType, long size, byte[] content) {
        Validator.notEmptyOrNull(id, "file id");
        Validator.notEmptyOrNull(name, "file name");
        Validator.notEmptyOrNull(contentType, "file contentType");
        Validator.greaterThan(size, 0, "file size");
        Validator.notNull(content, "file content");
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }

    public byte[] getContent() {
        return content;
    }
}

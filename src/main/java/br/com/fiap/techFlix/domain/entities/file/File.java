package br.com.fiap.techFlix.domain.entities.file;

import br.com.fiap.techFlix.domain.validation.Validator;

public class File {

    private String id;
    private String name;
    private String contentType;
    private long size;
    private byte[] content;

    public File(String id, String name, String contentType, long size, byte[] content) {
        Validator.notEmptyOrNull(id, "file id");
        Validator.notEmptyOrNull(name, "file name");
        Validator.notEmptyOrNull(contentType, "file contentType");
        Validator.greaterThan(size, 0, "file size");
        Validator.objectNotNull(content, "file content");
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

}

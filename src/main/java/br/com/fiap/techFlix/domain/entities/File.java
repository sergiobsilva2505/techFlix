package br.com.fiap.techFlix.domain.entities;

import org.springframework.util.Assert;

public class File {

    private String id;
    private String name;
    private String contentType;
    private long size;
    private byte[] content;

    public File(String id, String name, String contentType, long size, byte[] content) {
        Assert.hasText(id, "Id must not be empty");
        Assert.hasText(name, "Name must not be empty");
        Assert.hasText(contentType, "Content type must not be empty");
        Assert.isTrue(size > 0, "Size must be greater than zero");
        Assert.notNull(content, "Content must not be null");
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

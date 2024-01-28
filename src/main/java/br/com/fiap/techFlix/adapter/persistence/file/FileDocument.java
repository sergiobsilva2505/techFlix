package br.com.fiap.techFlix.adapter.persistence.file;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "files")
public class FileDocument {

    @Id
    private String id;
    private String name;
    private String contentType;
    private long size;
    private byte[] content;

    public FileDocument() {
    }

    public FileDocument(String id, String name, String contentType, long size, byte[] content) {
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

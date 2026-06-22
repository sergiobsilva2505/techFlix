package br.com.fiap.techflix.adapter.web.file;

import br.com.fiap.techflix.adapter.persistence.file.FileDocument;
import br.com.fiap.techflix.domain.entities.file.File;

public class FileMapper {

    private FileMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static File toDomain(FileDocument fileDocument) {
        return new File(fileDocument.getId(), fileDocument.getName(), fileDocument.getContentType(), fileDocument.getSize(), fileDocument.getContent());
    }

    public static FileShowDTO toView(File file) {
        return new FileShowDTO(file.getId(), file.getName(), file.getContentType(), file.getSize());
    }
}

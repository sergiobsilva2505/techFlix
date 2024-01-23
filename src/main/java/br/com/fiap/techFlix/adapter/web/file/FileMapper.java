package br.com.fiap.techFlix.adapter.web.file;

import br.com.fiap.techFlix.domain.entities.file.File;
import br.com.fiap.techFlix.adapter.persistence.file.FileDocument;

public class FileMapper {

    public static File toDomain(FileDocument fileDocument) {
        return new File(fileDocument.getId(), fileDocument.getName(), fileDocument.getContentType(), fileDocument.getSize(), fileDocument.getContent());
    }

    public static FileShowDTO toView(File file) {
        return new FileShowDTO(file.getId(), file.getName(), file.getContentType(), file.getSize());
    }
}

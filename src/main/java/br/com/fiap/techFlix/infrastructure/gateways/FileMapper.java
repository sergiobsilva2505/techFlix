package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.domain.entities.File;
import br.com.fiap.techFlix.infrastructure.controllers.FileShowDTO;
import br.com.fiap.techFlix.infrastructure.persistence.FileDocument;

public class FileMapper {

    public static File toDomain(FileDocument fileDocument) {
        return new File(fileDocument.getId(), fileDocument.getName(), fileDocument.getContentType(), fileDocument.getSize(), fileDocument.getContent());
    }

    public static FileDocument toPersistence(File file) {
        return new FileDocument(file.getId(), file.getName(), file.getContentType(), file.getSize(), file.getContent());
    }

    public static FileShowDTO toView(File file) {
        return new FileShowDTO(file.getId(), file.getName(), file.getContentType(), file.getSize());
    }
}

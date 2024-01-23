package br.com.fiap.techFlix.adapter.web.video;

import br.com.fiap.techFlix.adapter.web.Operation;
import br.com.fiap.techFlix.application.ports.VideoSearchPort;

import java.time.LocalDate;
import java.util.Optional;

public record VideoSearchDTO(Integer page, Integer size, String title, String categoryName, LocalDate publicationDate,
                             Operation publicationDateOperation) implements VideoSearchPort {

    @Override
    public Integer page() {
        return Optional.ofNullable(page).orElse(0);
    }

    @Override
    public Integer size() {
        return Optional.ofNullable(size).orElse(10);
    }

    public boolean hasTitle() {
        return title != null && !title.isBlank();
    }

    public boolean hasCategoryName() {
        return categoryName != null && !categoryName.isBlank();
    }

    public boolean hasPublicationDate() {
        return publicationDate != null && hasPublicationDateOperation();
    }

    public boolean hasPublicationDateOperation() {
        return publicationDateOperation != null;
    }


}

package br.com.fiap.techFlix.infrastructure.controllers;

import java.time.LocalDate;
import java.util.Optional;

public record SearchVideoDTO(Integer page, Integer size, String title, String categoryName, LocalDate publicationDate, Operation publicationDateOperation) {

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

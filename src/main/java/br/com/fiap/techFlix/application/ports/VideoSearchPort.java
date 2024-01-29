package br.com.fiap.techFlix.application.ports;

import java.time.LocalDate;

public interface VideoSearchPort {

    Integer page();

    Integer size();

    String title();

    String categoryName();

    LocalDate publicationDate();

    Operation publicationDateOperation();

    boolean hasTitle();

    boolean hasCategoryName();

    boolean hasPublicationDate();

    Direction sort();

    boolean hasSort();
}

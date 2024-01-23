package br.com.fiap.techFlix.application.ports;

import br.com.fiap.techFlix.adapter.web.Operation;

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
}

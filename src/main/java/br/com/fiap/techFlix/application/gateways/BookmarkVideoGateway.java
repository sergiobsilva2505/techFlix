package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.BookmarkVideo;

import java.util.Optional;

public interface BookmarkVideoGateway {

    BookmarkVideo save(BookmarkVideo bookmarkVideo);
    Optional<BookmarkVideo> findByName(String name);
    Optional<BookmarkVideo> findByUserId(String userId);
}

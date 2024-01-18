package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.BookmarkVideoGateway;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.persistence.BookmarkVideoDocument;
import br.com.fiap.techFlix.infrastructure.persistence.BookmarkVideoRepository;

import java.util.Optional;

public class BookmarkVideoRepositoryGateway implements BookmarkVideoGateway {

    private final BookmarkVideoRepository bookmarkVideoRepository;

    public BookmarkVideoRepositoryGateway(BookmarkVideoRepository bookmarkVideoRepository) {
        this.bookmarkVideoRepository = bookmarkVideoRepository;
    }

    @Override
    public BookmarkVideo save(BookmarkVideo bookmarkVideo) {
        BookmarkVideoDocument saved = bookmarkVideoRepository.save(BookmarkVideoMapper.toPersistence(bookmarkVideo));
        return BookmarkVideoMapper.toDomain(saved);
    }

    @Override
    public Optional<BookmarkVideo> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<BookmarkVideo> findByUserId(String userId) {
        return Optional.empty();
    }
}

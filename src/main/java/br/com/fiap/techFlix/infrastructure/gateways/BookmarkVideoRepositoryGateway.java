package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.persistence.BookmarkVideoDocument;
import br.com.fiap.techFlix.infrastructure.persistence.BookmarkVideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
    public Optional<BookmarkVideo> findByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public PagePort<BookmarkVideo> allBookmarkVideo(int page, int size) {
        Page<BookmarkVideo> bookmarkVideos = bookmarkVideoRepository.findAll(PageRequest.of(page, size)).map(BookmarkVideoMapper::toDomain);

        return new PageDTO<>(bookmarkVideos);
    }

    @Override
    public Optional<BookmarkVideo> findByVideoIdAndUserId(String videoId, String userId) {
        return bookmarkVideoRepository.findByVideoIdAndUserId(videoId, userId).map(BookmarkVideoMapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        bookmarkVideoRepository.deleteById(id);
    }
}

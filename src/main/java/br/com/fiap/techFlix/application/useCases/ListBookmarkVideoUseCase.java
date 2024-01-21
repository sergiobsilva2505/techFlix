package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.controllers.BookmarkVideoShowDTO;
import br.com.fiap.techFlix.infrastructure.gateways.BookmarkVideoMapper;

public class ListBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;

    public ListBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
    }

    public BookmarkVideo listBookmarkVideo(String userId) {
        return bookmarkVideoGateway.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));
    }

    public PagePort<BookmarkVideoShowDTO> listAllBookmarkVideo(int page, int size) {
        PagePort<BookmarkVideo> videoPagePort = bookmarkVideoGateway.allBookmarkVideo(page, size);

        return videoPagePort.map(BookmarkVideoMapper::toView);
    }
}

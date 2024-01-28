package br.com.fiap.techFlix.application.useCases.bookmark;

import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.adapter.web.bookmarkvideo.BookmarkVideoShowDTO;
import br.com.fiap.techFlix.adapter.web.bookmarkvideo.BookmarkVideoMapper;

public class ListBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;

    public ListBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
    }

    public BookmarkVideo listBookmarkVideo(String bookmarkId) {
        return bookmarkVideoGateway.findById(bookmarkId).orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));
    }

    public PagePort<BookmarkVideoShowDTO> listAllBookmarkVideo(int page, int size) {
        PagePort<BookmarkVideo> videoPagePort = bookmarkVideoGateway.allBookmarkVideo(page, size);

        return videoPagePort.map(BookmarkVideoMapper::toView);
    }
}

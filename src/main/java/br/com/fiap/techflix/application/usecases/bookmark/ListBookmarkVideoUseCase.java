package br.com.fiap.techflix.application.usecases.bookmark;

import br.com.fiap.techflix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;
import br.com.fiap.techflix.adapter.web.bookmarkvideo.BookmarkVideoShowDTO;
import br.com.fiap.techflix.adapter.web.bookmarkvideo.BookmarkVideoMapper;

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

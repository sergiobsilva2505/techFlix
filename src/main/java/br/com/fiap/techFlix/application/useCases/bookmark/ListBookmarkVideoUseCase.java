package br.com.fiap.techFlix.application.useCases.bookmark;

import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.adapter.web.bookmarkvideo.BookmarkVideoShowDTO;
import br.com.fiap.techFlix.adapter.web.bookmarkvideo.BookmarkVideoMapper;

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

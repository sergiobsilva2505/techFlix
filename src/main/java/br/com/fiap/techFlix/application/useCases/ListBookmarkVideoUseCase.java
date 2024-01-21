package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.BookmarkVideoGateway;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;

import java.util.List;

public class ListBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;

    public ListBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
    }

    public BookmarkVideo listBookmarkVideo(String userId) {
        return bookmarkVideoGateway.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));
    }

    public List<BookmarkVideo> listAllBookmarkVideo() {
        return bookmarkVideoGateway.allBookmarkVideo();
    }
}

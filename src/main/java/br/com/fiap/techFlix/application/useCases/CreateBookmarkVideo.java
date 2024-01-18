package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.BookmarkVideoGateway;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.controllers.BookmarkVideoCreateDTO;

public class CreateBookmarkVideo {

    private final BookmarkVideoGateway bookmarkVideoGateway;

    public CreateBookmarkVideo(BookmarkVideoGateway bookmarkVideoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
    }

    public BookmarkVideo createBookmarkVideo(BookmarkVideoCreateDTO bookmarkVideoCreateDTO) {
        return bookmarkVideoGateway.save(new BookmarkVideo(bookmarkVideoCreateDTO.user(), bookmarkVideoCreateDTO.video(), bookmarkVideoCreateDTO.bookmark()));
    }
}

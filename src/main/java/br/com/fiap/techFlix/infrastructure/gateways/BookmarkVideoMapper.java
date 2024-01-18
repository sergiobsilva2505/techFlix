package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.controllers.BookmarkVideoShowDTO;
import br.com.fiap.techFlix.infrastructure.persistence.BookmarkVideoDocument;

public class BookmarkVideoMapper {

    public static BookmarkVideo toDomain(BookmarkVideoDocument bookmarkVideoDocument) {
        return new BookmarkVideo(bookmarkVideoDocument.getUser(), bookmarkVideoDocument.getVideo(), bookmarkVideoDocument.isBookmark());
    }

    public static BookmarkVideoDocument toPersistence(BookmarkVideo bookmarkVideo) {
        return new BookmarkVideoDocument(bookmarkVideo.getUser(), bookmarkVideo.getVideo(), bookmarkVideo.isBookmark());
    }

    public static BookmarkVideoShowDTO toView(BookmarkVideo bookmarkVideo) {
        return new BookmarkVideoShowDTO(bookmarkVideo.getId(), bookmarkVideo.getUser(), bookmarkVideo.getVideo(), bookmarkVideo.isBookmark());
    }
}

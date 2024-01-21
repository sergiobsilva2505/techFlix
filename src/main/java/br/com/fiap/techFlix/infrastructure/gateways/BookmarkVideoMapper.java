package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.infrastructure.controllers.BookmarkVideoShowDTO;
import br.com.fiap.techFlix.infrastructure.persistence.BookmarkVideoDocument;

public class BookmarkVideoMapper {

    public static BookmarkVideo toDomain(BookmarkVideoDocument bookmarkVideoDocument) {
        return new BookmarkVideo(bookmarkVideoDocument.getId(), UserMapper.toDomain(bookmarkVideoDocument.getUser()), VideoMapper.toDomain(bookmarkVideoDocument.getVideo()));
    }

    public static BookmarkVideoDocument toPersistence(BookmarkVideo bookmarkVideo) {
        return new BookmarkVideoDocument(UserMapper.toPersistence(bookmarkVideo.getUser()), VideoMapper.toPersistence(bookmarkVideo.getVideo()));
    }

    public static BookmarkVideoShowDTO toView(BookmarkVideo bookmarkVideo) {
        return new BookmarkVideoShowDTO(bookmarkVideo.getId(), UserMapper.toView(bookmarkVideo.getUser()), VideoMapper.toView(bookmarkVideo.getVideo()));
    }
}

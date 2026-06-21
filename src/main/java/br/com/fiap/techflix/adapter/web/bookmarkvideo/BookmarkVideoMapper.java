package br.com.fiap.techflix.adapter.web.bookmarkvideo;

import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;
import br.com.fiap.techflix.adapter.web.user.UserMapper;
import br.com.fiap.techflix.adapter.web.video.VideoMapper;
import br.com.fiap.techflix.adapter.persistence.bookmarkvideo.BookmarkVideoDocument;
import br.com.fiap.techflix.domain.entities.user.User;
import br.com.fiap.techflix.domain.entities.video.Video;

public class BookmarkVideoMapper {

    private BookmarkVideoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static BookmarkVideo toDomain(BookmarkVideoDocument bookmarkVideoDocument) {
        return new BookmarkVideo(bookmarkVideoDocument.getId(), UserMapper.toDomain(bookmarkVideoDocument.getUser()), VideoMapper.toDomain(bookmarkVideoDocument.getVideo()));
    }

    public static BookmarkVideoDocument toPersistence(User user, Video video) {
        return new BookmarkVideoDocument(null, UserMapper.toPersistence(user), VideoMapper.toPersistence(video));
    }

    public static BookmarkVideoShowDTO toView(BookmarkVideo bookmarkVideo) {
        return new BookmarkVideoShowDTO(bookmarkVideo.getId(), UserMapper.toView(bookmarkVideo.getUser()), VideoMapper.toView(bookmarkVideo.getVideo()));
    }
}

package br.com.fiap.techFlix.adapter.web.bookmarkvideo;

import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.adapter.web.user.UserMapper;
import br.com.fiap.techFlix.adapter.web.video.VideoMapper;
import br.com.fiap.techFlix.adapter.persistence.bookmarkvideo.BookmarkVideoDocument;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;

public class BookmarkVideoMapper {

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

package br.com.fiap.techFlix.infrastructure.persistence;

import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.domain.entities.Video;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookmarkVideos")
public class BookmarkVideoDocument {

    private String id;
    private User user;
    private Video video;
    private boolean bookmark;

    public BookmarkVideoDocument(String id, User user, Video video, boolean bookmark) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.bookmark = bookmark;
    }

    public BookmarkVideoDocument(User user, Video video, boolean bookmark) {
        this.user = user;
        this.video = video;
        this.bookmark = bookmark;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Video getVideo() {
        return video;
    }

    public boolean isBookmark() {
        return bookmark;
    }
}

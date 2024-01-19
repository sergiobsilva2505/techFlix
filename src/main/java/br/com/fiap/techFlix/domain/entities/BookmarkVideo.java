package br.com.fiap.techFlix.domain.entities;

import br.com.fiap.techFlix.domain.validation.Validator;

public class BookmarkVideo {

    private String id;
    private User user;
    private Video video;
    private boolean bookmark;

    public BookmarkVideo(User user, Video video, boolean bookmark) {
        Validator.objectNotNull(user, "bookmark video user");
        Validator.objectNotNull(video, "bookmark user");
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
